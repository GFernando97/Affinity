package com.affinity.affinityteam.affinity.Notifications;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAtbk-7cw:APA91bFGBkj7Fk8DDwZ7Lj8Bl0NeAVcrSbzNVJZ9vwlcGeT2H6YlvzEu_qyaGjtaKIzVOr4HFsSnU4pHybCKU8YE_uuM4-p_UWW1N3jaucjjCnOXbfnT9WvFDqRfnvggDhx7ZqbDBbqv"

            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
