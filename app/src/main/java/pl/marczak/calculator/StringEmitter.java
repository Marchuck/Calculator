package pl.marczak.calculator;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;

/**
 * Project "Calculator"
 * <p>
 * Created by Lukasz Marczak
 * on 16.11.2016.
 */

public class StringEmitter {
    public static Observable<String> from(final EditText editText) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            boolean subscribed = true;

            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                if (!subscribed) return;

                final TextWatcher listener = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.length() > 0) {
                            emitter.onNext(charSequence.toString());
                            subscribed = true;
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                };

                editText.addTextChangedListener(listener);

                emitter.setDisposable(new Disposable() {
                    @Override
                    public void dispose() {
                        editText.removeTextChangedListener(listener);
                        subscribed = false;
                    }

                    @Override
                    public boolean isDisposed() {
                        return !subscribed;
                    }
                });

            }
        });
    }
}
