package zlc.season.rxjava2demo;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import zlc.season.rxjava2demo.entity.LoginRequest;
import zlc.season.rxjava2demo.entity.LoginResponse;
import zlc.season.rxjava2demo.entity.RegisterRequest;
import zlc.season.rxjava2demo.entity.RegisterResponse;
import zlc.season.rxjava2demo.entity.UserBaseInfoRequest;
import zlc.season.rxjava2demo.entity.UserBaseInfoResponse;
import zlc.season.rxjava2demo.entity.UserExtraInfoRequest;
import zlc.season.rxjava2demo.entity.UserExtraInfoResponse;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/12/6
 * Time: 11:30
 * FIXME
 */
public interface Api {
    @GET
    Observable<LoginResponse> login(@Body LoginRequest request);

    @GET
    Observable<RegisterResponse> register(@Body RegisterRequest request);

    @GET
    Observable<UserBaseInfoResponse> getUserBaseInfo(@Body UserBaseInfoRequest request);

    @GET
    Observable<UserExtraInfoResponse> getUserExtraInfo(@Body UserExtraInfoRequest request);
}
