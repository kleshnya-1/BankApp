//package ru.laptseu.bankapp.core;
//
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.MongoClientURI;
//import com.mongodb.client.MongoCollection;
//import org.bson.codecs.configuration.CodecRegistries;
//import org.bson.codecs.configuration.CodecRegistry;
//import org.bson.codecs.pojo.ClassModel;
//import org.bson.codecs.pojo.PojoCodecProvider;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import ru.laptseu.bankapp.models.CurrencyRate;
//import ru.laptseu.bankapp.models.CustomDocument;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
//@Configuration
//public class MongoConfig {
//
//    @Bean
//    public String string(){
//        return new String();
//    }
//
//    @Bean
//    public static   CodecRegistry getCodeсRegistry() {
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
//
//    @Bean
//    public static MongoCollection getMongoCollection(CodecRegistry codecRegistry) {
//        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://1:1@cluster0.vlexj.mongodb.net/test"));
//        MongoCollection collection = mongoClient
//                .getDatabase("123")
//                .withCodecRegistry(codecRegistry).getCollection("CurrencyRates_Spring", CurrencyRate.class);
//        return collection;
//    }
////@Bean
////    public static MongoCollection getMongoCollection(String collectionName, Class clazz) {
////        CodecRegistry codecRegistry = getCodeсRegistry();
////        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://1:1@cluster0.vlexj.mongodb.net/test"));
////        MongoCollection collection = mongoClient
////                .getDatabase("123")
////                .withCodecRegistry(codecRegistry).getCollection(collectionName, clazz);
////        // mongoClient.close();
////        return collection;
////    }
//}
