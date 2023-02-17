package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream


fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    val token = "fLmEtQWWTrKSfmjiQxp96p:APA91bFekKsHprQTqNbA4Fy4RT6QtxlcyuY89LiFr8xzc3U8hw-kU99P6wakd6OHDGdhmDrfs1jwppXGEVoSM1iXGciBr-xxHxSipShlyY-qqJsA6LvUGKEIGQHA-lJgWmDfvOOCR3HP"
    val longText ="Автобус, тяжело вздохнув, наконец остановился на автовокзале. Из окон разлился шум города, а на улице стояла зимняя прохлада. Пассажиры быстро выходили из автобуса, увлеченно перекрикивая друг друга и торопливо собирая свои вещи. Некоторые схватывали чемоданы, другие пытались убедить своих друзей присоединиться к ним на общественном транспорте, но все они были рады, что наконец-то достигли своего пункта назначения. В это время на улице начался медленный снегопад, окутывающий все вокруг мягкой, белой пеленой."

    val messageLike = Message.builder()
        .putData("action", "LIKE")
        .putData("content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token)
        .build()

    val messageNewPost = Message.builder()
        .putData("action", "NEWPOST")
        .putData("content", """{
            "userId": 1,
            "author": "Vasiliy",
            "postId": 2,
            "postContent": "$longText"
        }""".trimIndent())
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(messageNewPost)
}
