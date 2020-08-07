package soohan530.google.com.suhang_3

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.File
import java.io.FileInputStream

class MainActivity : AppCompatActivity() {
    var path = ""
    var permission_list = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission()

        // 텍스트 입력 _1 (데이터를 가져옴)
        button.setOnClickListener { v->
            path = Environment.getExternalStorageDirectory().absolutePath + "/android/data/"+packageName

            var file = File(path)
            if(file.exists() == false){
                file.mkdir()
            }

            var arr : ArrayList<Stringall> = ArrayList<Stringall>()

            try{
                var input = FileInputStream(path+"/sdFile3.dat")
                var dis = DataInputStream(input)




                while(true){
                    var str1 = dis.readUTF()
                    Log.d("str1",str1)
                    if(str1 == null){
                        break
                    }
                    var st1 = str1 + "\n"
                    var str2 = dis.readUTF()
                    var str3 = dis.readUTF()
                    var str4 = dis.readUTF()
                    var str5 = dis.readUTF()

                    //Stringall은 사용자 정의 데이터 클래스
                    var data : Stringall = Stringall(str1,str2,str3,str4,str5)
                    arr.add(data)
                }


                dis.close()
            }catch(e : Exception){
                e.printStackTrace()
            }



            // 텍스트 입력 _2 (데이터를 다시 넣음)

            var output = java.io.FileOutputStream(path+"/sdFile3.dat",false)
            var dos = DataOutputStream(output)

            var newData = Stringall(noText.text.toString(),nameText.text.toString(),phoneText.text.toString(),ageText.text.toString(),dateText.text.toString())

            var a = 0

            for(i in 0..(arr.size - 1)){
                if(arr[i]._1.equals(newData._1)){
                    arr[i] = newData
                    a = 1
                }

            }

            if(a == 0){
                arr.add(newData)
            }
            for(i in arr){
                dos.writeUTF(i._1)
                dos.writeUTF(i._2)
                dos.writeUTF(i._3)
                dos.writeUTF(i._4)
                dos.writeUTF(i._5)
            }

            dos.flush()
            dos.close()
        }

        button2.setOnClickListener { v->
            var intent : Intent = Intent(this,Main2Activity::class.java)
            startActivity(intent)
        }
    }

    fun checkPermission(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return
        }
        for(permission : String in permission_list){
            var chk = checkCallingOrSelfPermission(permission)
            if(chk == PackageManager.PERMISSION_DENIED){
                requestPermissions(permission_list,0)
                break
            }
        }

    }
}
