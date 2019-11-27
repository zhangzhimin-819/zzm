package mobileshop.edu.huatec.com.mobileshop.http.service;

import java.util.List;

import mobileshop.edu.huatec.com.mobileshop.http.entity.HttpResult;
import mobileshop.edu.huatec.com.mobileshop.http.entity.MemberEntity;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface MemberService {
    @FormUrlEncoded
    @POST("member/login2")
    Observable<HttpResult<MemberEntity>> login2(
            @Field("input") String input,
            @Field("password") String password
    );

    //用户注册
    @FormUrlEncoded
    @POST("member")
    Observable<HttpResult<MemberEntity>> register(
            @Field("uname") String uname,
            @Field("password") String password,
            @Field("emile") String emile);

    //修改密码
    @FormUrlEncoded
    @POST("member/{memberId}")
    Observable<HttpResult> changePassword(
            @Field("memberId") String memberId,
            @Field("old_pwd") String old_pwd,
            @Field("new_pwd") String new_pwd
    );
}
