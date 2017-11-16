package com.landsoft.sharedpreferencesputgetremove;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnWrite, btnRead, btnRomove, btnRemoveAll;
    public static final String SHARED_PREFERENCES_NAME = "landsoft.com";
    public static final String SHARED_PREFERENCES_NAME1 = "trantuan.com";
    public static final String STRING_VALUE = "STRING_VALUE";
    public static final String BOOLEAN_VALUE = "BOOLEAN_VALUE";
    public static final String FLOAT_VALUE = "FLOAT_VALUE";
    public static final String LONG_VALUE = "LONG_VALUE";
    public static final String INT_VALUE = "INT_VALUE";
    public static final String STRING_SET_VALUE = "STRING_SET_VALUE";
    private final String TAG = getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWrite = findViewById(R.id.btn_write);
        btnRead = findViewById(R.id.btn_read);
        btnRomove = findViewById(R.id.btn_remove);
        btnRemoveAll = findViewById(R.id.btn_remove_all);
        btnWrite.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnRomove.setOnClickListener(this);
        btnRemoveAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_write:
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(STRING_VALUE, "tran minh tuan");
                editor.putBoolean(BOOLEAN_VALUE, true);
                editor.putFloat(FLOAT_VALUE, 1.22f);
                editor.putInt(INT_VALUE, 25);
                editor.putLong(LONG_VALUE, 9200000);
//                Set<> la kieu du lieu duy nhat, no se xoa het tat ca gia tri giong nhau
//                HashSet la mot loai interface khong theo thu tu, co nghia la no la khong co tham so nhu list.position
                Set<String> setString = new HashSet<>();
                setString.add("d");
                setString.add("b");
                setString.add("b");
                setString.add("a");
                setString.add("c");
                setString.add("a");

                //                Iterator do du lieu trong Hashset
                Iterator<String> ite = setString.iterator();
                while (ite.hasNext()) {
                    Log.d(TAG, "onClick: " + ite.next());

                }


                editor.putStringSet(STRING_SET_VALUE, setString);

                boolean result = editor.commit();
//                de ket qua trinh phai goi lenh commit hoach apply. su khac bit cua commit va apply nam o cho commit la dong bo va tra ve true hoac false chon nguoi bit, con app la khong dong bo chay nhung hon nhung khong bao la ghi dc hay ko dc
                Log.d(TAG, "onClick: " + result);
                break;
            case R.id.btn_read:
                SharedPreferences sharedPreferences1 = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
                String mString = sharedPreferences1.getString(STRING_VALUE, "NEU RONG");
                boolean mBoolean = sharedPreferences1.getBoolean(BOOLEAN_VALUE, true);
                float mFloat = sharedPreferences1.getFloat(FLOAT_VALUE, 0.0f);
                int mInt = sharedPreferences1.getInt(INT_VALUE, 0);
                long mLong = sharedPreferences1.getLong(LONG_VALUE, 1111);
                Set<String> setString1 = new HashSet<String>();
                setString1 = sharedPreferences1.getStringSet(STRING_SET_VALUE, null);
                Log.d(TAG, "mString: " + mString + "\n" +
                        "mBoolean: " + mBoolean + "\n" +
                        "mFloat: " + mFloat + "\n" +
                        "mInt: " + mInt + "\n" +
                        "mLong: " + mLong + "\n" +
                        "setString1: " + setString1 + "\n"
                );

                break;
            case R.id.btn_remove:
                SharedPreferences sharedPreferences2 = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedPreferences2.edit();
                editor2.remove(STRING_SET_VALUE);
                editor2.apply();
                break;
            case R.id.btn_remove_all:
                SharedPreferences sharedPreferences3 = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor3 = sharedPreferences3.edit();
                editor3.clear();
                editor3.apply();
                break;
        }
    }
}
