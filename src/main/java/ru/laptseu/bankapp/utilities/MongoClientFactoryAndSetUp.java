package ru.laptseu.bankapp.utilities;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.PojoCodecProvider;
import ru.laptseu.bankapp.models.CurrencyRate;

import java.util.HashMap;
import java.util.Map;

public class MongoClientFactoryAndSetUp {

    //todo ask. стоит ли разделять монго СЕТАП и фабрику?
    //todo note. пока что в фабрике только один класс
    //todo note. реализован через облачный монго. через виртуальную манину поднял линукс,
    // докер, в нем монго, а код через идею и плагин Code With Me с синхронизацией кода между
    // системами. но не смог установить соединение, не зная, какой порт открывает монго в докере.
    // потому перешел на облачное в основной системе.

    private static final String MONGO_URL = "mongodb+srv://1:1@cluster0.vlexj.mongodb.net/test";
    private static Map<Class, String> classMap = new HashMap<>();

    static {
        classMap.put(CurrencyRate.class, "-'st bank rates");
    }

    private static CodecRegistry getCodexRegistry() {
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(
                                ClassModel.builder(CurrencyRate.class).enableDiscriminator(true).build()
                        ).automatic(true)
                        .build()
                )
        );
        return codecRegistry;
    }

    public static MongoCollection getMongoCollection(Integer id, Class clazz) {
        CodecRegistry codecRegistry = getCodexRegistry();

        String collectionName = classMap.get(clazz);
        if (id != null) collectionName = id.toString() + collectionName;
        MongoClient mongoClient = new MongoClient(new MongoClientURI(MONGO_URL));
        MongoCollection<CurrencyRate> collection = mongoClient
                .getDatabase("123")
                .withCodecRegistry(codecRegistry).getCollection(collectionName, clazz);
        mongoClient.close();
        return collection;
    }

    public static MongoCollection getMongoCollection(Class clazz) {
        return getMongoCollection(null, clazz);
    }

}
