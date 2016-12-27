package pl.marczak.calculator;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Cancellable;

/**
 * Project "Calculator"
 * <p>
 * Created by Lukasz Marczak
 * on 27.12.2016.
 */

public class RxUtils {

    public static <T> Observable<T> toObservable(@NonNull final ObservableField<T> observableField) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(final ObservableEmitter<T> emitter) throws Exception {
                final android.databinding.Observable.OnPropertyChangedCallback callback =
                        new android.databinding.Observable.OnPropertyChangedCallback() {
                            @Override
                            public void onPropertyChanged(android.databinding.Observable dataBindingObservable, int propertyId) {
                                if (dataBindingObservable == observableField) {
                                    emitter.onNext(observableField.get());
                                }
                            }
                        };
                observableField.addOnPropertyChangedCallback(callback);
                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        observableField.removeOnPropertyChangedCallback(callback);
                    }
                });
            }
        });
    }
}