//package ru.laptseu.bankapp.core;
//
//import com.mongodb.MongoClientSettings;
//import com.mongodb.MongoClientURI;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import org.bson.codecs.configuration.CodecRegistries;
//import org.bson.codecs.configuration.CodecRegistry;
//import org.bson.codecs.pojo.ClassModel;
//import org.bson.codecs.pojo.PojoCodecProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.mongodb.MongoDatabaseFactory;
//import org.springframework.stereotype.Component;
//import ru.laptseu.bankapp.models.CurrencyRate;
//import ru.laptseu.bankapp.models.CustomDocument;
//
//import java.util.Set;
//@Component
//public class MongoFactory implements MongoDatabaseFactory {
//
//    @Autowired
//     private MongoClient mongoClient;
//    @Override
//    public MongoDatabase getDatabase() throws DataAccessException {
//        return null;
//    }
//
//    @Override
//    public MongoDatabase getDatabase(String dbName) throws DataAccessException {
//        return null;
//    }
//
//    public MongoCollection getMongoCollection(Integer id, Class clazz) {
//        CodecRegistry codecRegistry = getCodeсRegistry();
//
//       // com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient(new MongoClientURI(MONGO_URL));
//        MongoCollection collection = mongoClient
//                .getDatabase("123")
//                .withCodecRegistry(codecRegistry).getCollection(clazz.getSimpleName() + "s", clazz);
//        return collection;
//    }
//
//    public MongoCollection getMongoCollection(String collectionName, Class clazz) {
//        CodecRegistry codecRegistry = getCodeсRegistry();
//        //com.mongodb.MongoClient mongoClient = new com.mongodb.MongoClient(new MongoClientURI(MONGO_URL));
//        MongoCollection collection = mongoClient
//                .getDatabase("123")
//                .withCodecRegistry(codecRegistry).getCollection(collectionName, clazz);
//        return collection;
//    }
//
//    private static CodecRegistry getCodeсRegistry() {
//        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
//                MongoClientSettings.getDefaultCodecRegistry(),
//                CodecRegistries.fromProviders(PojoCodecProvider.builder()
//                        .register(
//                                ClassModel.builder(CurrencyRate.class).enableDiscriminator(true).build(),
//                                ClassModel.builder(Set.class).enableDiscriminator(true).build(),
//                                ClassModel.builder(CustomDocument.class).enableDiscriminator(true).build()
//                        ).automatic(true)
//                        .build()
//                )
//        );
//        return codecRegistry;
//    }
//}
