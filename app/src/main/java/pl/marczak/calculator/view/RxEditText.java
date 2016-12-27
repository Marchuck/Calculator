package pl.marczak.calculator.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
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
 * on 27.12.2016.
 */

public class RxEditText extends EditText {
    public RxEditText(Context context) {
        super(context);
    }

    public RxEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RxEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RxEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);

        if (text == null) setSelection(0);
        else setSelection(text.length());
    }

    public Observable<CharSequence> asObservable(){
        return Observable.create(new ObservableOnSubscribe<CharSequence>() {
            @Override
            public void subscribe(final ObservableEmitter<CharSequence> emitter) throws Exception {
                final TextWatcher watcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        emitter.onNext(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                };
                addTextChangedListener(watcher);

                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        removeTextChangedListener(watcher);
                    }
                });
            }
        });
    }
}
