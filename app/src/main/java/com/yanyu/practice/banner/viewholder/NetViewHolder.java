package com.yanyu.practice.banner.viewholder;

import android.view.View;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.yanyu.practice.R;
import com.yanyu.practice.banner.net.BannerData;
import com.yanyu.practice.banner.view.CornerImageView;
import com.zhpan.bannerview.BaseViewHolder;
import com.zhpan.bannerview.utils.BannerUtils;

/**
 * <pre>
 *   Created by zhangpan on 2019-08-14.
 *   Description:
 * </pre>
 */
public class NetViewHolder extends BaseViewHolder<BannerData> {

    public NetViewHolder(@NonNull View itemView) {
        super(itemView);
        CornerImageView imageView = findView(R.id.banner_image);
        imageView.setRoundCorner(BannerUtils.dp2px(0));
    }

    @Override
    public void bindData(BannerData data, int position, int pageSize) {
        CornerImageView imageView = findView(R.id.banner_image);
        Glide.with(imageView).load(data.getImagePath()).placeholder(R.drawable.placeholder).into(imageView);
    }
}
