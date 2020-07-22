/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yanyu.practice.update.http;

import androidx.annotation.NonNull;

import com.xuexiang.xupdate.proxy.IUpdateHttpService;
import com.xuexiang.xupdate.utils.UpdateUtils;
import com.yanyu.practice.update.utils.DownloadUtil;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 使用okhttp
 *
 * @author xuexiang
 * @since 2018/7/10 下午4:04
 */
public class OKHttpUpdateHttpService implements IUpdateHttpService {

    private boolean mIsPostJson;
    private OkHttpClient okHttpClient;
    private Call call;

    public OKHttpUpdateHttpService() {
        this(false);
    }

    public OKHttpUpdateHttpService(boolean isPostJson) {
        mIsPostJson = isPostJson;
    }


    @Override
    public void asyncGet(@NonNull String url, @NonNull Map<String, Object> params, final @NonNull Callback callBack) {
//        OkHttpUtils.get()
//                .url(url)
//                .params(transform(params))
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        callBack.onError(e);
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        callBack.onSuccess(response);
//                    }
//                });
    }

    @Override
    public void asyncPost(@NonNull String url, @NonNull Map<String, Object> params, final @NonNull Callback callBack) {
        //这里默认post的是Form格式，使用json格式的请修改 post -> postString
//        RequestCall requestCall;
//        if (mIsPostJson) {
//            requestCall = OkHttpUtils.postString()
//                    .url(url)
//                    .content(UpdateUtils.toJson(params))
//                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
//                    .build();
//        } else {
//            requestCall = OkHttpUtils.post()
//                    .url(url)
//                    .params(transform(params))
//                    .build();
//        }
//        requestCall
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        callBack.onError(e);
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        callBack.onSuccess(response);
//                    }
//                });
    }

    @Override
    public void download(@NonNull String url, @NonNull String path, @NonNull String fileName, final @NonNull DownloadCallback callback) {

        okHttpClient = new OkHttpClient.Builder().
                connectTimeout(1, TimeUnit.HOURS).
                readTimeout(1, TimeUnit.HOURS).
                build();
        Request request = new Request.Builder().url(url).build();
        call = okHttpClient.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callback.onError(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, fileName);
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中更新进度条
                        callback.onProgress(progress,total);
                    }
                    fos.flush();
                    // 下载完成
                    callback.onSuccess(file);
                } catch (Exception e) {
                    callback.onError(e);
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    @Override
    public void cancelDownload(@NonNull String url) {
        call.cancel();
    }

    private Map<String, String> transform(Map<String, Object> params) {
        Map<String, String> map = new TreeMap<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            map.put(entry.getKey(), entry.getValue().toString());
        }
        return map;
    }


}