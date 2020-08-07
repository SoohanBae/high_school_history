package soohan530.google.com.suhang_3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import kotlinx.android.synthetic.main.activity_main2.*
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //텍스트 보여주는 코드
        var path = Environment.getExternalStorageDirectory().absolutePath + "/android/data/"+packageName

        var file = File(path)
        if(file.exists() == false){
            file.mkdir()
        }
        var str = ""
        try{
            var input = FileInputStream(path+"/sdFile3.dat")
            var dis = DataInputStream(input)




            while(true){
                var str1 = dis.readUTF()
                Log.d("str1",str1)
                if(str1 == null){
                    break
                }
                str += str1 + "\n"
                str += dis.readUTF() + "\n"
                str += dis.readUTF() + "\n"
                str += dis.readUTF() + "\n"
                str += dis.readUTF() + "\n----\n\n----\n"


            }



            dis.close()
        }catch(e : Exception){
            e.printStackTrace()
        }
        textView.append("\n\n----아래부터 데이터----\n"+str)


    }
}
