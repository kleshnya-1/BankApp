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
import ru.laptseu.bankapp.models.CustomDocument;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class MongoClientFactoryAndSetUp {

    //todo ask. стоит ли разделять монго СЕТАП и фабрику?
    //todo note. пока что в фабрике только один класс

    private static final String MONGO_URL = "mongodb+srv://1:1@cluster0.vlexj.mongodb.net/test";
    private static final Map<Class, String> classMap = new HashMap<>();

    static {
        classMap.put(CurrencyRate.class, "-'st bank rates");
    }

    private static CodecRegistry getCodeсRegistry() {
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder()
                        .register(
                                ClassModel.builder(CurrencyRate.class).enableDiscriminator(true).build(),
                                ClassModel.builder(Set.class).enableDiscriminator(true).build(),
                                ClassModel.builder(CustomDocument.class).enableDiscriminator(true).build()
                        ).automatic(true)
                        .build()
                )
        );
        return codecRegistry;
    }

    public static MongoCollection getMongoCollection(Integer id, Class clazz) {
        CodecRegistry codecRegistry = getCodeсRegistry();

        // String collectionName = classMap.get(clazz);
        //if (id != null) collectionName = id.toString() + collectionName;
        MongoClient mongoClient = new MongoClient(new MongoClientURI(MONGO_URL));
        MongoCollection collection = mongoClient
                .getDatabase("123")
                .withCodecRegistry(codecRegistry).getCollection(clazz.getSimpleName() + "s", clazz);
        // mongoClient.close();
        return collection;
    }

    public static MongoCollection getMongoCollection(String collectionName, Class clazz) {
        CodecRegistry codecRegistry = getCodeсRegistry();
        MongoClient mongoClient = new MongoClient(new MongoClientURI(MONGO_URL));
        MongoCollection collection = mongoClient
                .getDatabase("123")
                .withCodecRegistry(codecRegistry).getCollection(collectionName, clazz);
        // mongoClient.close();
        return collection;
    }

//    public static MongoCollection getMongoCollection(Class clazz) {
//        return getMongoCollection(null, clazz);
//    }

}
