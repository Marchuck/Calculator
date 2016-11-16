package pl.marczak.calculator;

import android.view.View;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Project "Calculator"
 * <p>
 * Created by Lukasz Marczak
 * on 16.11.2016.
 */

public class Clicks {
    public static Observable<Void> from(final View v) {
        return Observable.create(new ObservableOnSubscribe<Void>() {
            @Override
            public void subscribe(final ObservableEmitter<Void> e) throws Exception {
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        e.onNext(null);
                    }
                });
            }
        });
    }
}
