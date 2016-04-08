package com.hz.along.baidumap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.GroundOverlayOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    MapView mMapView = null;
    BaiduMap mBaiduMap = null;
    private Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();//获取地图

        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);//设置显示普通地图
        //mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);//设置显示卫星图
        //mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);//设置显示空白地图

        mBaiduMap.setTrafficEnabled(false);//设置是否开启实时交通图
        mBaiduMap.setBaiduHeatMapEnabled(false);//设置是否开启城市热力图

        mMapView.setLogoPosition(LogoPosition.logoPostionCenterTop);//设置百度地图LOGO位置
        mMapView.setPadding(150, 0,0,0);//设置LOGO的左上右下内边距

        Toast.makeText(this, "当前比例尺的大小为"+mMapView.getMapLevel(), Toast.LENGTH_LONG).show();

    /*    *//**
         * 在地图上显示Maker
         *//*
        //定义Maker坐标点
        LatLng point = new LatLng(39.963175, 116.400244);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.ic_launcher);
        //构建MarkerOption，用于在地图上添加Marker
  *//*     OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);*//*

        *//**
         * 设置可拖拽
         *//*
        OverlayOptions options = new MarkerOptions()
                .position(point)  //设置marker的位置
                .icon(bitmap)  //设置marker图标
                .zIndex(9)  //设置marker所在层级
                .draggable(true);  //设置手势拖拽
        //将marker添加到地图上
        marker = (Marker) (mBaiduMap.addOverlay(options));

        *//**
         * 设置拖拽的监听事件
         *//*
        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            public void onMarkerDrag(Marker marker) {
                //拖拽中
                Toast.makeText(MainActivity.this, "拖拽中", Toast.LENGTH_LONG).show();
            }
            public void onMarkerDragEnd(Marker marker) {
                //拖拽结束
                Toast.makeText(MainActivity.this, "拖拽结束", Toast.LENGTH_LONG).show();
                marker.remove();//移除
            }
            public void onMarkerDragStart(Marker marker) {
                //开始拖拽
                Toast.makeText(MainActivity.this, "开始拖拽", Toast.LENGTH_LONG).show();

            }
        });*/

        /**
         * 添加几何覆盖物
         */

        //定义多边形的五个顶点
        LatLng pt1 = new LatLng(39.93923, 116.357428);
        LatLng pt2 = new LatLng(39.91923, 116.327428);
        LatLng pt3 = new LatLng(39.89923, 116.347428);
        LatLng pt4 = new LatLng(39.89923, 116.367428);
        LatLng pt5 = new LatLng(39.91923, 116.387428);
        ArrayList<LatLng> pts = new ArrayList<LatLng>();
        pts.add(pt1);
        pts.add(pt2);
        pts.add(pt3);
        pts.add(pt4);
        pts.add(pt5);
        //构建用户绘制多边形的Option对象
        OverlayOptions polygonOption = new PolygonOptions()
                .points(pts)
                .stroke(new Stroke(5, 0xAA00FF00))
                .fillColor(0xAAFFFF00);
        //在地图上添加多边形Option，用于显示
        mBaiduMap.addOverlay(polygonOption);

        /**
         * 添加文字覆盖物
         */
        //定义文字所显示的坐标点
        LatLng llText = new LatLng(39.86923, 116.397428);
        //构建文字Option对象，用于在地图上添加文字
        OverlayOptions textOption = new TextOptions()
                .bgColor(0xAAFFFF00)
                .fontSize(24)
                .fontColor(0xFFFF00FF)
                .text("百度地图SDK")
                .rotate(-30)
                .position(llText);
        //在地图上添加该文字对象并显示
        mBaiduMap.addOverlay(textOption);

        //创建InfoWindow展示的view
        Button button = new Button(getApplicationContext());
        button.setBackgroundResource(R.mipmap.ic_launcher);
        //定义用于显示该InfoWindow的坐标点
        LatLng pt = new LatLng(39.86923, 116.397428);
        //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
        InfoWindow mInfoWindow = new InfoWindow(button, pt, -47);
        //显示InfoWindow
        mBaiduMap.showInfoWindow(mInfoWindow);

        /**
         * 地形图图层
         */
        LatLng southwest = new LatLng(39.92235, 116.380338);
        LatLng northeast = new LatLng(39.947246, 116.414977);
        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(northeast)
                .include(southwest)
                .build();
//定义Ground显示的图片
        BitmapDescriptor bdGround = BitmapDescriptorFactory
                .fromResource(R.mipmap.ic_launcher);
//定义Ground覆盖物选项
        OverlayOptions ooGround = new GroundOverlayOptions()
                .positionFromBounds(bounds)
                .image(bdGround)
                .transparency(0.8f);
//在地图中添加Ground覆盖物
        mBaiduMap.addOverlay(ooGround);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}

