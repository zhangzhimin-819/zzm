package mobileshop.edu.huatec.com.mobileshop.http.presenter;

import java.util.List;
import rx.Observable;

import mobileshop.edu.huatec.com.mobileshop.http.HttpMethods;
import mobileshop.edu.huatec.com.mobileshop.http.entity.GoodsEntity;
import rx.Subscriber;

public class GoodsPresenter extends HttpMethods {
    public static void listByKeyWords(Subscriber<List<GoodsEntity>> subscriber, String keywords) {
        Observable<List<GoodsEntity>> observable = goodsService.listByKeywords(keywords)
                .map(new HttpResultFunc<List<GoodsEntity>>());
        toSubscribe(observable, subscriber);
    }


    public static void list(Subscriber<List<GoodsEntity>> subscriber,int catId){
        Observable<List<GoodsEntity>> observable=goodsService.list(catId)
                .map(new HttpResultFunc<List<GoodsEntity>>());
        toSubscribe(observable,subscriber);
    }

}
