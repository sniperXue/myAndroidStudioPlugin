//package com.vpedak.testsrecorder.plugin.core;
//
//import com.intellij.psi.PsiDirectory;
//import com.intellij.psi.PsiElement;
//import com.intellij.psi.PsiFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
///**
// * Created by XueYun on 2018/4/8.
// */
//public class MyFindClasses {
//    private static MyFindClasses instance = new MyFindClasses();
//    private Map<String, String> myFindClasses = new HashMap<String, String>();
//    private PsiDirectory psiDirectory;
//    private MyFindClasses() {
//    }
//
//    public static MyFindClasses getInstance() {
//        return instance;
//    }
//
//    //这块传进去androidtest包
//    public String getRobotiumClass(PsiElement psiElement) {
//
//        /**
//         * 1.先获取androidtest包下面的所有文件
//         * 2.按 文件名—文件 的方式存到myFindClasses这个映射中去
//         * 3.
//         */
//
//
//
//            StringBuilder build = new StringBuilder();
//            byte[] buf = new byte[1024];
//            InputStream is = (InputStream) (psiElement.getText());
//            try {
//                int length;
//                while ((length = is.read(buf)) != -1) {
//                    build.append(new String(buf, 0, length));
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    is.close();
//                } catch (IOException ignore) {
//                }
//            }
//            return build.toString();
//        }
//}
//
