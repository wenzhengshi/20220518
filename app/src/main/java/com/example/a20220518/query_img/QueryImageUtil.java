package com.example.a20220518.query_img;



import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: baipenggui
 * Date: 2019/1/24 16:06
 * Email: 1528354213@qq.com
 * Description: 从sdcard中查询出保存的图片
 */
public class QueryImageUtil {
    private static ArrayList<Bitmap> imageList = new ArrayList<>();
    private static List<String> imagePathList = new ArrayList<>();
    private static QueryImageUtil mInstance;
    private static String mFileRootPath;
    private static Bitmap bitmap;
    private static String filePath;
    private static Context mContext;
    private static String TAG;
    private static boolean successCallOne = false;//查询成功的接口只调用一侧
    private static boolean failureCallOne = false;//查询成功的接口只调用一侧
    private static OnQueryListener onQueryListener;

    /*
     *@Description: 获取查询图片的实例,这是第一步
     *@Params:
     *@Author: baipenggui
     *@Date: 2019/1/24
     */
    public static QueryImageUtil getmInstance(Context context) {
        mContext = context;
        if (mInstance == null) {
            mInstance = new QueryImageUtil();
        }
        TAG = mContext.getClass().getName();

        return mInstance;
    }

    /*
     *@Description:初始化sdcard文件  这是第三步
     *@Params: fileRootPath  存储文件的根路径
     *@params: editcontent  输入框中的内容
     *@Author: baipenggui
     *@Date: 2019/1/24
     */
    public static ArrayList<Bitmap> initFile(String fileRootPath, String editcontent) {
        mFileRootPath = fileRootPath;
        System.out.println("文件路径："+mFileRootPath);
        //获取路径是否成功
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            filePath = fileRootPath ;
            System.out.println("filePath文件路径："+filePath);
        }

        //添加之前先清空集合
        imageList.clear();
        //获取图片路径集合
        List<String> imagePathList = getImagePathFromSD();

        File mFile = new File(filePath);
        //文件夹不存在则创建
        if (!mFile.exists()) {
            mFile.mkdir();
        } else {
            //遍历路径集合,判读路径找回功能是否存在输入框中获取的内容
            for (int i = 0; i < imagePathList.size(); i++) {
                Log.d(TAG, "imagePathList: " + imagePathList.get(i));
                String imagePath = imagePathList.get(i);
                if (editcontent.length() > 0 || editcontent != null) {
                    boolean contains = imagePath.contains(editcontent);
                    //如果存在,则将已存在该字段的路径通过BitmapFactory.decodeFile转化为bitmap格式,通过接口获取查询结果
                    if (contains) {
                        bitmap = BitmapFactory.decodeFile(imagePathList.get(i));
                        imageList.add(bitmap);
                        if (successCallOne == false) {
                            Log.d(TAG, "onQueryListener: " + onQueryListener);
                            if (onQueryListener != null) {
                                onQueryListener.onQuerySuccess("查询成功");
                            }
                            successCallOne = true;
                        }

                    } else {
                        if (failureCallOne == false) {
                            if (onQueryListener != null) {
                                onQueryListener.onQueryFailure("没有发现该车辆");
                            }
                        }
                        failureCallOne = true;
                    }
                } else {
                    Log.d(TAG, "editcontent is null !");
                }

            }

            Log.d(TAG, "imageList: " + imageList);
        }

        return imageList;
    }

    /**
     * 从sd卡获取图片资源
     */
    private static List<String> getImagePathFromSD() {
        //添加之前先清空集合
        imagePathList.clear();
        // 得到sd卡内plate文件夹的路径
        String filePath = mFileRootPath.toString();
        System.out.println("fmFileRootPath文件路径："+filePath);
        // 得到该路径文件夹下所有的文件
        File fileAll = new File(filePath);
        File[] files = fileAll.listFiles();
        if(files!=null) {
            System.out.println("有" + files.length + "个文件");
            if (files != null) {
                // 将所有的文件存入ArrayList中,并过滤所有图片格式的文件
                for (int i = 0; i < files.length; i++) {
                    File file = files[i];
                    if (checkIsImageFile(file.getPath())) {
                        imagePathList.add(file.getPath());
                    }
                }
            }
        }
        // 返回得到的图片列表
        return imagePathList;
    }

    /**
     * 检查扩展名，得到图片格式的文件
     */
    @SuppressLint("DefaultLocale")
    private static boolean checkIsImageFile(String fName) {
        boolean isImageFile = false;
        System.out.println("fName压压："+fName);
        //获取扩展名
        String FileEnd = fName.substring(fName.lastIndexOf(".") ,
                fName.length()).toLowerCase();
        if (FileEnd.equals("jpg") || FileEnd.equals("png") || FileEnd.equals("gif")
                || FileEnd.equals("jpeg") || FileEnd.equals("bmp")) {
            isImageFile = true;
        } else {
            isImageFile = false;
        }

        return isImageFile;
    }

    /*
     *@Description: 查询成功,失败结果的回调  这是第二步, 注意:调用接口需要在initFile()之前, 否则onQueryListener为空
     *@Params:
     *@Author: baipenggui
     *@Date: 2019/1/24
     */
    public static void setOnQueryListener(OnQueryListener onQuery) {
        onQueryListener = onQuery;
    }

    public interface OnQueryListener {
        void onQuerySuccess(String success);

        void onQueryFailure(String failure);
    }


}
