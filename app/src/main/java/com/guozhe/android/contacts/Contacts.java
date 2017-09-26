package com.guozhe.android.contacts;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guozhe on 2017. 9. 26..
 */

public class Contacts {
    public static List<Data> getContects(Context context){
        List<Data> datas = new ArrayList<>();
        //일종의 데이터 툴
        //전화번호부에 있는 Contack provider 를 통해 데이터를 가져 올수 있다
        ContentResolver resolver = context.getContentResolver();
        //전화번호 URI =ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        Uri phone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        //가져올 데이터의 컬럼명을 정의
        String projection[] = {ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                ,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                ,ContactsContract.CommonDataKinds.Phone.NUMBER};
        //쿼리를 날려서 데이터를 가져온다
        Cursor cursor = resolver.query(phone,projection,null,null,null);
        if(cursor != null){
            while (cursor.moveToNext()){
                int idIndex = cursor.getColumnIndex(projection[0]);
                int id = cursor.getInt(idIndex);
                int nameIndex = cursor.getColumnIndex(projection[1]);
                String name = cursor.getString(nameIndex);
                int telIndex = cursor.getColumnIndex(projection[2]);
                String tel = cursor.getString(telIndex);
                //내가 설게한 데이터 클래스에 담아준다
                Data data = new Data();
                data.setId(id);
                data.setName(name);
                data.setTel(tel);

                datas.add(data);
            }
        }
        Log.e("ddddddddd","========"+datas.toString());
        cursor.close();
        return  datas;
    }
}
