package by.bstu.fit.lyolia.laba1_andr_login_scr;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

/**
 * Created by User on 14.02.2018.
 */

public class AsteriskPasswordTransformationMethod extends PasswordTransformationMethod {
    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        return new PasswordCharSequence(source);
    }



    private class PasswordCharSequence implements CharSequence {

        int test = 67;

        private CharSequence mSource;

        public PasswordCharSequence(CharSequence mSource) {
            this.mSource = mSource;
        }

        @Override
        public int length() {
            return mSource.length();
        }

        @Override
        public char charAt(int i) {
            //test++;
            return (char) test;
        }


        @Override
        public CharSequence subSequence(int i, int i1) {
            return mSource.subSequence(i, i1);
        }
    }
}
