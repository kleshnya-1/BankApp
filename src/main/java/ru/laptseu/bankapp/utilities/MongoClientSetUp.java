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

public class MongoClientSetUp {
   //todo note. реализован через облачный монго. через виртуальную манину поднял линукс,
   // докер, в нем монго, а код через идею и плагин Code With Me с синхронизацией кода между
   // системами. но не смог установить соединение, не зная, какой порт открывает монго в докере.
   // потому перешел на облачное в основной системе.
    private static final String MONGO_PASS = "1";
    private static final String MONGO_URI = "mongodb+srv://1:" + MONGO_PASS + "@cluster0.vlexj.mongodb.net/test";
    private static final String MONGO_DATABASE = "123";
    private static final Map<Class, String> factoryMapCollectionNames = new HashMap<>();
    private static final CodecRegistry codecRegistry;

    static {
        factoryMapCollectionNames.put(CurrencyRate.class, "CurrencyRateMD");
        codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(
                                ClassModel.builder(CurrencyRate.class).enableDiscriminator(true).build())
                        .automatic(true)
                        .build()
                )
        );
    }

    public static MongoCollection getMongoCollection(Class clazz) {
        //todo ask клиент же закрылся сам?
        MongoCollection collectionCr;
        collectionCr = new MongoClient(new MongoClientURI(MONGO_URI))
                .getDatabase(MONGO_DATABASE)
                .withCodecRegistry(codecRegistry).
                        getCollection(factoryMapCollectionNames.get(clazz), clazz);
        return collectionCr;
    }
}
