package com.goldingmedia.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.goldingmedia.BaseActivity;
import com.goldingmedia.GDApplication;
import com.goldingmedia.R;
import com.goldingmedia.contant.Contant;
import com.goldingmedia.ethernet.HtmlRequest;
import com.goldingmedia.ethernet.IP;
import com.goldingmedia.goldingcloud.TruckMediaProtos;
import com.goldingmedia.most.GlobalSettings;
import com.goldingmedia.most.fblock.FBlock;
import com.goldingmedia.most.ts_renderer.TsReceiver;
import com.goldingmedia.mvp.view.activity.JmagazineActivity;
import com.goldingmedia.mvp.view.adapter.ImageAdapter;
import com.goldingmedia.mvp.view.animations.Animations;
import com.goldingmedia.mvp.view.lrc.LrcProcess;
import com.goldingmedia.mvp.view.lrc.LrcView;
import com.goldingmedia.mvp.view.ui.AutoPlayGallery;
import com.goldingmedia.mvp.view.ui.MTextView;
import com.goldingmedia.temporary.DataHelper;
import com.goldingmedia.temporary.GetProtocolBuffer;
import com.goldingmedia.temporary.Modes.Order;
import com.goldingmedia.temporary.Modes.PlayGroupMode;
import com.goldingmedia.temporary.Modes.PlayerConfigMode;
import com.goldingmedia.temporary.Variables;
import com.goldingmedia.utils.HandlerUtils;
import com.goldingmedia.utils.NLog;
import com.goldingmedia.utils.NToast;
import com.goldingmedia.utils.Utils;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.goldingmedia.R.id.surfaceView;
import static com.goldingmedia.contant.Contant.MasterId;
import static com.goldingmedia.temporary.ImageProcessing.ConvertBitMap;
import static com.goldingmedia.utils.Utils.onDemandRecording;

public class MediaPlayActivity extends BaseActivity implements
		OnTouchListener, OnClickListener, OnCompletionListener, OnPreparedListener,HandlerUtils.OnReceiveMessageListener {

	private static final int HANDLER_TIMER = 1;
	private static final int HANDLER_MEDIA_PLAY = 2;
	private static final int HANDLER_SHOW_CODEVIEW = 3;
	private static final int HANDLER_STATUS_MSG = 4;
	private static final int HANDLER_ADAPTER_VIEW = 5;
	private static final int HANDLER_UPDATE_MEDIA_INFO = 6;
	private static final int HANDLER_INIT_MEDIAPLAYER2 = 7;
	private static final int HANDLER_SHOW_TOAST = 8;
	private static final int HANDLER_MEDIA_LIST_SELECT = 9;
	private static final int HANDLER_PLAY_LRC = 10;

	private Context mContext;
	private TsReceiver m_TsReceiver;

	private SurfaceView mMovieView;

	private GroupAdapter mGroupAdapter;
	private MediaAdapter mMediaAdapter;
	private ListView mGroupListView;
	private ListView mMediaListView;

	private ImageButton mListButton;
	private RelativeLayout lilplaybar;
	private Button mPriviousButton;
	private Button mPlayButton;
	private Button mNextButton;
	private Button mEqButton;
	private Button playblack;

	private LinearLayout mLinearLayout;

	private final static long SHOW_TIME = 800;
	private final static long DELAY_TIME = 6 * 1000;
	private final static int LIST_HIDDEN_LF = 300;

	private Handler handler;
	private Runnable runnable;

	private ImageView musiccover;
	private MTextView musicsinger;
	private MTextView musicalbum;
	private MTextView musicname;
	private TextView musicnolrc;
	private LrcView musiclrc;
	private ImageView musiccover_full;
	private MTextView musicsinger_full;
	private MTextView musicalbum_full;
	private MTextView musicname_full;
	private LrcView musiclrc_full;
	private TextView musicnolrc_full;
	private TextView mEqText1;
	private TextView mEqText2;
	private TextView mEqText3;
	private TextView mEqText4;
	private TextView mEqText5;
	private List<LrcProcess.LrcContent> lrcList = new ArrayList<>();

	private TruckMediaProtos.CTruckMediaNode mTruck = null;

	private Typeface localTypeface;
	private TextView mDurationTextView;
	private TextView mTotalTimeTextView;
	private int mTotalTime = 0;
	private int mPlayDuration = 0;
	private int index = 0;
	private int CurrentTime = 0;

	private SeekBar mSeekBar;
	private SeekBar seekBarVolume;
	private LinearLayout movie_layout;
	private LinearLayout music_layout;
    private LinearLayout music_layout_full;
	private RelativeLayout relbody;
	private boolean mStop = true;
	private boolean firstStop = true;
	private boolean isWidescreen = false;
	private boolean isFullScreen = false;

	private int position = 0;
	private int classId = 0;
	private int classSubId = 0;
	private int classMainId = 0;
	private boolean isOnResumeRestart = true;
	private boolean isSurfaceChanged = false;
	private boolean isSurfaceMediaPlayer = false;
	private boolean isInitMediaPlayer = false;

	private TextView mPaidPrice;
	private LinearLayout mPaidTipsLay;
	private LinearLayout mQRCodeLay;
	private TextView mStatusMsg;
	private WebView mCodeView;
	private int mProgress = 0;
	private FBlock mFBlock;
	private byte[] payloadMac = new byte[4];
	public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;

    private LinearLayout rightLay;
    private VideoView mTopVideo;
	private AutoPlayGallery mMidImg;
	private AutoPlayGallery mBottomImg;
	private ImageView mCrossImg;

	private int mTopCount;
	private int mTxtCount;
	private int mCrossCount;
	private List<TruckMediaProtos.CTruckMediaNode> mWindowTxtList;
	private List<TruckMediaProtos.CTruckMediaNode> mWindowTopList;
	private  ArrayList<Bitmap> MidBitMapArray= new ArrayList<Bitmap>();
	private  ArrayList<Bitmap> BottomBitMapArray= new ArrayList<Bitmap>();
	private  ArrayList<Bitmap> CrossBitMapArray= new ArrayList<Bitmap>();
	private LinearLayout mFlipperLay;
	private MTextView mFlipper;
	private Boolean isPauseActivity = false;
	private Boolean fileEnd = false;
	private Boolean isOnCreate = true;
	private AudioManager am;
	private int mTimerCounter = 0;
	private boolean isWindowUpdate = false;

	private PlayerConfigMode playerConfigMode;
	private HandlerUtils.HandlerHolder handlerHolder;

	private Timer timer = new Timer( );
	private TimerTask task = new TimerTask( ) {
		public void run ( ) {
			Message message = new Message( );
			message.what = HANDLER_TIMER;
			handlerHolder.sendMessage(message);
		}
	};

	@SuppressLint("SetJavaScriptEnabled")
	private void setWebView() {
		mCodeView.getSettings().setJavaScriptEnabled(true);
		mCodeView.setWebViewClient(new WebViewClient(){
			@SuppressLint("LongLogTag")
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				Log.e("shouldOverrideUrlLoading", url);
				return true;
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;

		Intent mIntentMsg = new Intent("com.goldingmedia.system.load.script");
		mIntentMsg.putExtra("scriptpath",Contant.SWITCH_HEADPHONE);
		mContext.sendBroadcast(mIntentMsg);

		am =(AudioManager)getSystemService(Context.AUDIO_SERVICE);
		getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
		int width = mDisplayMetrics.widthPixels;
		if(width == 1280){
			isWidescreen = true;
		}
		localTypeface = Typeface.createFromAsset(getAssets(), "font/zfgy.otf");

		Intent intent = getIntent();
		position = intent.getIntExtra("position", 0);
		classId = intent.getIntExtra("classId", 0);
		classSubId = intent.getIntExtra("classSubId", 0);
		classMainId = intent.getIntExtra("classMainId", 0);
		Log.i("","-----onCreate()position=" + position);
		Log.i("","-----onCreate()classMainId=" + classMainId);

		setContentView(R.layout.activity_media_play);
		initWindowAds();
		initView();
		initListener();
		setWebView();
		hideBar();

		playerConfigMode = GetProtocolBuffer.getPlayerConfigMode();
		ArrayList<PlayGroupMode> groupModeList = GetProtocolBuffer.getGroupList(playerConfigMode);
		mGroupAdapter = new GroupAdapter(this, groupModeList);
		mGroupAdapter.setSelectPosition(getGroupAdapterPosition());
		mGroupListView.setAdapter(mGroupAdapter);
		if (classId == Contant.CATEGORY_MEDIA_ID) {
		} else {
			mGroupListView.setVisibility(View.GONE);
		}
		mMediaAdapter = new MediaAdapter(this);
		mMediaAdapter.setSelectPositionFirst(position);
		mMediaListView.setAdapter(mMediaAdapter);

		mMovieView.setVisibility(View.VISIBLE);
		handlerHolder = new HandlerUtils.HandlerHolder(this);
		timer.schedule(task, 100,1000);
	}

	private void  initWindowAds(){
		List<TruckMediaProtos.CTruckMediaNode> list;

		list = GDApplication.getmInstance().getTruckMedia().getcAds().getWindowOrientTrucksMap(Contant.ADS_WINDOW_ORIENT_MIDDLE);
		for(int i=0;i<list.size();i++){
			TruckMediaProtos.CTruckMediaNode truck = list.get(i);
			String path = Contant.getTruckMetaNodePath(truck.getMediaInfo().getCategoryId(),truck.getMediaInfo().getCategorySubId(), truck.getMediaInfo().getTruckMeta().getTruckFilename()+"/"+truck.getMediaInfo().getTruckMeta().getTruckFilename()+".jpg",true);
			MidBitMapArray.add(ConvertBitMap(path));
		}
		list = GDApplication.getmInstance().getTruckMedia().getcAds().getWindowOrientTrucksMap(Contant.ADS_WINDOW_ORIENT_BOTTOM);
		for(int i=0;i<list.size();i++){
			TruckMediaProtos.CTruckMediaNode truck = list.get(i);
			String path = Contant.getTruckMetaNodePath(truck.getMediaInfo().getCategoryId(),truck.getMediaInfo().getCategorySubId(), truck.getMediaInfo().getTruckMeta().getTruckFilename()+"/"+truck.getMediaInfo().getTruckMeta().getTruckFilename()+".jpg",true);
			BottomBitMapArray.add(ConvertBitMap(path));
		}
		list = GDApplication.getmInstance().getTruckMedia().getcAds().getWindowOrientTrucksMap(Contant.ADS_WINDOW_ORIENT_CROSSBAR);
		for(int i=0;i<list.size();i++){
			TruckMediaProtos.CTruckMediaNode truck = list.get(i);
			String path = Contant.getTruckMetaNodePath(truck.getMediaInfo().getCategoryId(),truck.getMediaInfo().getCategorySubId(), truck.getMediaInfo().getTruckMeta().getTruckFilename()+"/"+truck.getMediaInfo().getTruckMeta().getTruckFilename()+".jpg",true);
			CrossBitMapArray.add(ConvertBitMap(path));
		}
		mWindowTxtList  = GDApplication.getmInstance().getTruckMedia().getcAds().getExtendTypeTrucksMap(Contant.ADS_EXTEND_TYPE_MGR);
	}

	private int getGroupAdapterPosition() {// 获取组列表的选中项
		if (classMainId == Contant.MEDIA_TYPE_MOVIE) {
			if (playerConfigMode.category_sub) {
				for (int i = 0; i < playerConfigMode.category_sub_order.length; i++) {
					if (classSubId == playerConfigMode.category_sub_order[i]) {
						return i;
					}
				}
			} else {
				for (int i = 0; i < playerConfigMode.category_sub_order.length; i++) {
					if (classSubId == playerConfigMode.category_sub_order[i]) {
						return playerConfigMode.category_main_order.length + i;
					}
				}
			}
		} else {
			if (playerConfigMode.category_sub) {
				for (int i = 0; i < playerConfigMode.category_main_order.length; i++) {
					if (classMainId == playerConfigMode.category_main_order[i]) {
						return playerConfigMode.category_sub_order.length + i;
					}
				}
			} else {
				for (int i = 0; i < playerConfigMode.category_main_order.length; i++) {
					if (classMainId == playerConfigMode.category_main_order[i]) {
						return i;
					}
				}
			}
		}
		return 0;
	}

	private void initView() {
		movie_layout = (LinearLayout)findViewById(R.id.movie_layout);
		music_layout = (LinearLayout)findViewById(R.id.music_layout);
        music_layout_full = (LinearLayout)findViewById(R.id.music_layout_full);
		relbody = (RelativeLayout) findViewById(R.id.relbody);
		mMovieView = (SurfaceView)findViewById(surfaceView);
        rightLay = (LinearLayout)findViewById(R.id.rightLay);
		mTopVideo = (VideoView)findViewById(R.id.videotop);
		mMidImg = (AutoPlayGallery)findViewById(R.id.imgmid);
		mBottomImg = (AutoPlayGallery)findViewById(R.id.imgbottom);
		mCrossImg = (ImageView)findViewById(R.id.cross);
		mFlipperLay = (LinearLayout)findViewById(R.id.flipperLay);
		mFlipper = (MTextView)findViewById(R.id.flipper);

		ImageAdapter adapterM = new ImageAdapter(this, MidBitMapArray);
		mMidImg.setAdapter(adapterM);
		ImageAdapter adapterB = new ImageAdapter(this, BottomBitMapArray);
		mBottomImg.setAdapter(adapterB);


		mLinearLayout = (LinearLayout)findViewById(R.id.linearLayout1);
		switch (classId) {
			case Contant.CATEGORY_MEDIA_ID:// 多媒体
				mGroupListView = (ListView)findViewById(R.id.groupListView);
				mMediaListView = (ListView)findViewById(R.id.mediaListView);
				break;

			default:// 其他：
				mGroupListView = (ListView)findViewById(R.id.mediaListView);
				mMediaListView = (ListView)findViewById(R.id.groupListView);
				LinearLayout listViewLay2 = (LinearLayout) findViewById(R.id.listViewLay2);
				listViewLay2.setVisibility(View.GONE);
				break;
		}
		mListButton =(ImageButton)findViewById(R.id.listbar);
		mListButton.getBackground().setAlpha(150); //设置透明度
		lilplaybar = (RelativeLayout) findViewById(R.id.lilplaybar);
		mDurationTextView = (TextView)findViewById(R.id.txtduration);
		mTotalTimeTextView = (TextView)findViewById(R.id.txttotal);
		mDurationTextView.setTypeface(localTypeface);
		mTotalTimeTextView.setTypeface(localTypeface);
		mSeekBar = (SeekBar)findViewById(R.id.seekBar);
		seekBarVolume = (SeekBar)findViewById(R.id.seekBarVolume);
		mPriviousButton = (Button)findViewById(R.id.btnprevious);
		mNextButton = (Button)findViewById(R.id.btnnext);
		mPlayButton = (Button)findViewById(R.id.btnstop);
		mEqButton = (Button)findViewById(R.id.btnfunc);
		playblack = (Button)findViewById(R.id.playblack);

		mQRCodeLay = (LinearLayout)findViewById(R.id.qrcode_lay);
		mStatusMsg = (TextView)findViewById(R.id.status_msg);
		mCodeView = (WebView)findViewById(R.id.codeview);
		mPaidPrice = (TextView)findViewById(R.id.paid_price);
		mPaidTipsLay = (LinearLayout)findViewById(R.id.paid_tips_lay);

		musiccover = (ImageView)findViewById(R.id.musiccover);
		musicsinger = (MTextView)findViewById(R.id.musicsinger);
		musicalbum = (MTextView)findViewById(R.id.musicalbum);
		musicname = (MTextView)findViewById(R.id.musicname);
		musiclrc = (LrcView)findViewById(R.id.lrc);
		musicnolrc = (TextView)findViewById(R.id.nolrc);
		musicsinger.setTypeface(localTypeface);
		musicalbum.setTypeface(localTypeface);
		musicname.setTypeface(localTypeface);
		musiccover_full = (ImageView)findViewById(R.id.musiccover_full);
		musicsinger_full = (MTextView)findViewById(R.id.musicsinger_full);
		musicalbum_full = (MTextView)findViewById(R.id.musicalbum_full);
		musicname_full = (MTextView)findViewById(R.id.musicname_full);
		musiclrc_full = (LrcView)findViewById(R.id.lrc_full);
		musicnolrc_full = (TextView)findViewById(R.id.nolrc_full);
		musicsinger_full.setTypeface(localTypeface);
		musicalbum_full.setTypeface(localTypeface);
		musicname_full.setTypeface(localTypeface);
	}

	private void initListener() {
		lilplaybar.setOnClickListener(this);
		mPriviousButton.setOnClickListener(this);
		mNextButton.setOnClickListener(this);
		mPlayButton.setOnClickListener(this);
		mEqButton.setOnClickListener(this);
		playblack.setOnClickListener(this);

		mTopVideo.setOnTouchListener(this);
		mMidImg.setOnTouchListener(this);
		mBottomImg.setOnTouchListener(this);
		mPaidTipsLay.setOnTouchListener(this);

		mTopVideo.setOnCompletionListener(this);
		mTopVideo.setOnPreparedListener(this);

		mMovieView.getHolder().addCallback(new SurfaceHolder.Callback() {
			@Override
			public void surfaceDestroyed(SurfaceHolder arg0) { }
			@Override
			public void surfaceCreated(SurfaceHolder arg0) { }
			@Override
			public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
				if (!isInitMediaPlayer && mTruck != null) {
					isSurfaceMediaPlayer = true;
					InitMediaPlayer();
				}
				isSurfaceChanged = true;
			}
		});

		mSeekBar.setOnSeekBarChangeListener(new ProgressSeekBarChange());
		seekBarVolume.setOnSeekBarChangeListener(new VolumeSeekBarChange());
		seekBarVolume.setProgress(am.getStreamVolume(AudioManager.STREAM_MUSIC));

		mGroupListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				mGroupAdapter.setSelectPosition(position);
				mGroupAdapter.notifyDataSetChanged();
				InitMediaListView();
			}
		});

		mMediaListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				mGroupAdapter.setPlayGroup();
				mMediaAdapter.setTrucksToPlsyTrucks();
				mMediaAdapter.setSelectPosition(position);
				mTruck = (TruckMediaProtos.CTruckMediaNode)mMediaAdapter.getItem(mMediaAdapter.getSelectPosition());
				InitMediaPlayer();
				onDemandRecording(true, Contant.PROPERTY_STATISTICS_MEDIA_ID, mTruck, mContext);
			}
		});

		relbody.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				handlerRemoveCallbacks();
				if (mLinearLayout.getVisibility() == View.VISIBLE) {
					if (lilplaybar.getVisibility() == View.GONE) {
						if(isWidescreen){
							Animations.playbarOpenWide(lilplaybar, SHOW_TIME);
						}else{
							Animations.playbarOpenNormal(lilplaybar, SHOW_TIME);
						}
						lilplaybar.setVisibility(View.VISIBLE);
					} else {
						lilplaybar.setVisibility(View.GONE);
						if(isWidescreen){
							Animations.movielistHidden(mLinearLayout, SHOW_TIME, 240);
							Animations.playbarHiddenWide(lilplaybar, SHOW_TIME);
						}else{
							Animations.movielistHidden(mLinearLayout, SHOW_TIME, isFullScreen?LIST_HIDDEN_LF:192);
							Animations.playbarHiddenNormal(lilplaybar, SHOW_TIME);
						}
					}
				} else {
					if(isWidescreen){
						Animations.movielistOpenWide(mLinearLayout, SHOW_TIME);
					}else{
						Animations.movielistOpenNormal(mLinearLayout, SHOW_TIME);
					}
					if (lilplaybar.getVisibility() == View.GONE) {
						if(isWidescreen){
							Animations.playbarOpenWide(lilplaybar, SHOW_TIME);
						}else{
							Animations.playbarOpenNormal(lilplaybar, SHOW_TIME);
						}
						lilplaybar.setVisibility(View.VISIBLE);
					}
				}
				hideBar();
			}
		});

		mListButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				handlerRemoveCallbacks();
				if (mLinearLayout.getVisibility() == View.VISIBLE) {
					if(isWidescreen){
						Animations.movielistHidden(mLinearLayout, SHOW_TIME, 240);
					}else{
						Animations.movielistHidden(mLinearLayout, SHOW_TIME, isFullScreen?LIST_HIDDEN_LF:192);
					}

				} else {
					if(isWidescreen){
						Animations.movielistOpenWide(mLinearLayout, SHOW_TIME);
					}else{
						Animations.movielistOpenNormal(mLinearLayout, SHOW_TIME);
					}
				}
				hideBar();
			}
		});

		mGroupListView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				hideBar();
				return false;
			}
		});

		mMediaListView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				hideBar();
				return false;
			}
		});
	}

	private void FlashAdMsg(){

		if( mCrossCount < CrossBitMapArray.size()-1 ){
			mCrossCount++;
		} else {
			mCrossCount = 0;
		}

		if( CrossBitMapArray.size() != 0 ) {
			mCrossImg.setImageBitmap(CrossBitMapArray.get(mCrossCount));
		}
		if (mTimerCounter % 40 == 0) {
			if(mTxtCount < mWindowTxtList.size()-1){
				mTxtCount++;
			} else {
				mTxtCount = 0;
			}

			LoadTextView(mWindowTxtList); //change txt every 40s
		}
	}

	private void PlayPreviousFile(){// 上一集
		hideMovieList();
		mMediaAdapter.setSelectPosition(mMediaAdapter.getSelectPosition() - 1);
		mTruck = (TruckMediaProtos.CTruckMediaNode)mMediaAdapter.getItem(mMediaAdapter.getSelectPosition());
		InitMediaPlayer();
		onDemandRecording(true, Contant.PROPERTY_STATISTICS_MEDIA_ID, mTruck, mContext);
	}

	private void PlayNextFile() {// 下一集
		mPlayDuration = 0;
		hideMovieList();
		mMediaAdapter.setSelectPosition(mMediaAdapter.getSelectPosition() + 1);
		mTruck = (TruckMediaProtos.CTruckMediaNode)mMediaAdapter.getItem(mMediaAdapter.getSelectPosition());
		InitMediaPlayer();
		onDemandRecording(true, Contant.PROPERTY_STATISTICS_MEDIA_ID, mTruck, mContext);
	}

	private synchronized void InitMediaPlayer() {
		isInitMediaPlayer = true;
		InitMediaPlayer(true);
	}

	private int mTruckShow = -1;
	private synchronized void InitMediaPlayer(boolean restart) {// 初始化mostMediaPlayer
		if (isPauseActivity) return;
		mMediaAdapter.notifyDataSetChanged();
		mMediaListView.setSelection(mMediaAdapter.getSelectPosition());
		if(m_TsReceiver == null) {
			m_TsReceiver = new TsReceiver(GlobalSettings.GetAudioSink(), GlobalSettings.GetSource());
		}
		if(mFBlock == null) {
			mFBlock = FBlock.GetInstance();
			isOnResumeRestart = true;
		}

		Log.i("", "-----classMainId = "+classMainId);
		Log.i("", "-----getTruckMediaType = "+mTruck.getMediaInfo().getTruckMeta().getTruckMediaType());
		Log.i("", "-----mTruckShow = "+mTruckShow);
		Log.i("", "-----getTruckShow = "+mTruck.getMediaInfo().getTruckMeta().getTruckShow());
		//播放窗口有四种模式 音乐全屏 音乐广告 电影全屏 电影广告，四种模式切换 或者 第一次show出Activity 都重新初始化MediaPlayer
		if (mTruckShow != mTruck.getMediaInfo().getTruckMeta().getTruckShow() ||
                (classMainId != mTruck.getMediaInfo().getTruckMeta().getTruckMediaType() &&
				(classMainId == Contant.MEDIA_TYPE_MUSIC || mTruck.getMediaInfo().getTruckMeta().getTruckMediaType() == Contant.MEDIA_TYPE_MUSIC))
                || isOnResumeRestart) {

			if(mTruck.getMediaInfo().getTruckMeta().getTruckShow() != mTruckShow) {// 切换窗口广告与否
				if(mTruck.getMediaInfo().getTruckMeta().getTruckShow() == Variables.TRUCK_SHOW_NORMAL) {
					if( mWindowTopList.size() != 0){
						mWinAdsPlayTime = System.currentTimeMillis();
						mTopVideo.setVisibility(View.VISIBLE);
						TruckMediaProtos.CTruckMediaNode truck = mWindowTopList.get(mTopCount);
						String path = Contant.getTruckMetaNodePath(truck.getMediaInfo().getCategoryId(),truck.getMediaInfo().getCategorySubId(), truck.getMediaInfo().getTruckMeta().getTruckFilename()+"/"+truck.getMediaInfo().getTruckMeta().getTruckFilename()+".mp4",true);
						mTopVideo.stopPlayback();
						mTopVideo.setVideoURI(Uri.parse(path));
						mTopVideo.start();
					}
				} else {
					mTopVideo.stopPlayback();
					mTopVideo.setVisibility(View.GONE);
				}
			}

            if (classMainId == mTruck.getMediaInfo().getTruckMeta().getTruckMediaType() && classMainId == Contant.MEDIA_TYPE_MUSIC
                    && !isOnResumeRestart) {// 如果只是音乐切换音乐，只初始化广告，不初始化mostMediaPlayer
                if(mTruck.getMediaInfo().getTruckMeta().getTruckShow() == Variables.TRUCK_SHOW_NORMAL) {
                    music_layout_full.setVisibility(View.GONE);
                    music_layout.setVisibility(View.VISIBLE);
					mFlipperLay.setVisibility(View.VISIBLE);
                    rightLay.setVisibility(View.VISIBLE);
					Log.i("","-----show music normal");
                } else {
                    music_layout_full.setVisibility(View.VISIBLE);
                    music_layout.setVisibility(View.GONE);
					mFlipperLay.setVisibility(View.GONE);
                    rightLay.setVisibility(View.GONE);
					Log.i("","-----show music full");
                }
                mTruckShow = mTruck.getMediaInfo().getTruckMeta().getTruckShow();
            } else {
                if ( null != mFBlock ) {
                    mFBlock.StopStram.Set();
                }
                if ( null != m_TsReceiver ) {
                    m_TsReceiver.SetPause(true);
                    m_TsReceiver.Stop();
                }
				m_TsReceiver = new TsReceiver(GlobalSettings.GetAudioSink(), GlobalSettings.GetSource());
				int j = 0;
				for (; j < 50; j++) {
					if (isPauseActivity) return;
                    if (mFBlock.MediaPid.Get()) {
						if ( null == m_TsReceiver ) break;
                        if ( !m_TsReceiver.GetIsRunning() ) {
							Log.i("","-----!m_TsReceiver.GetIsRunning()");
							if(mTruck.getMediaInfo().getTruckMeta().getTruckMediaType() != Contant.MEDIA_TYPE_MUSIC) {// 如果是视频，初始化SurfaceView大小和广告
								ViewGroup.LayoutParams lp = mMovieView.getLayoutParams();
								if(mTruck.getMediaInfo().getTruckMeta().getTruckShow() == Variables.TRUCK_SHOW_NORMAL) {
									lp.width = 802;
									lp.height = 470;
									mMovieView.getHolder().setFixedSize(802, 470);
									mMovieView.setLayoutParams(lp);
									Log.i("","-----show movie normal");
								} else {
									lp.width = 1024;
									lp.height = 600;
									mMovieView.getHolder().setFixedSize(1024, 600);
									mMovieView.setLayoutParams(lp);
									Log.i("","-----show movie full");
								}
							} else if (mTruckShow != Variables.TRUCK_SHOW_NORMAL) {
								ViewGroup.LayoutParams lp = mMovieView.getLayoutParams();
								lp.width = 802;
								lp.height = 470;
								mMovieView.getHolder().setFixedSize(802, 470);
								mMovieView.setLayoutParams(lp);
								Log.i("","-----show music normal");

								if((mTruck.getMediaInfo().getTruckMeta().getTruckMediaType() == Contant.MEDIA_TYPE_MUSIC) && restart) {
									try {
										Thread.sleep(100);
									} catch (InterruptedException ignored) {
									}
									Message message = new Message( );
									message.what = HANDLER_INIT_MEDIAPLAYER2;
									handlerHolder.sendMessage(message);
									return;
								}
							}

                            // 初始化广告窗口
                            if(mTruck.getMediaInfo().getTruckMeta().getTruckShow() == Variables.TRUCK_SHOW_NORMAL) {
								mFlipperLay.setVisibility(View.VISIBLE);
                                rightLay.setVisibility(View.VISIBLE);
                            } else {
								mFlipperLay.setVisibility(View.GONE);
                                rightLay.setVisibility(View.GONE);
                            }

                            // 音乐mostMediaPlayer初始化
                            if(mTruck.getMediaInfo().getTruckMeta().getTruckMediaType() == Contant.MEDIA_TYPE_MUSIC) {
                                movie_layout.setVisibility(View.GONE);
                                if(mTruck.getMediaInfo().getTruckMeta().getTruckShow() == Variables.TRUCK_SHOW_NORMAL) {
                                    music_layout_full.setVisibility(View.GONE);
                                    music_layout.setVisibility(View.VISIBLE);
                                } else {
                                    music_layout_full.setVisibility(View.VISIBLE);
                                    music_layout.setVisibility(View.GONE);
                                }
								handlerHolder.sendEmptyMessageDelayed(HANDLER_PLAY_LRC, 200);
                                m_TsReceiver.Start(
                                        null,
                                        mFBlock.MediaPid.GetAudioPid(),
                                        0xFFFF);
                            } else {
                                // 电影mostMediaPlayer初始化
                                music_layout.setVisibility(View.GONE);
                                music_layout_full.setVisibility(View.GONE);
                                movie_layout.setVisibility(View.VISIBLE);
                                m_TsReceiver.Start(
                                        mMovieView.getHolder().getSurface(),
                                        mFBlock.MediaPid.GetAudioPid(),
                                        mFBlock.MediaPid.GetVideoPid());
                            }
                            isOnResumeRestart = false;
                            classMainId = mTruck.getMediaInfo().getTruckMeta().getTruckMediaType();
                            mTruckShow = mTruck.getMediaInfo().getTruckMeta().getTruckShow();
							break;
                        }
                    } else {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ignored) {
                        }
                    }
                }
				if (j == 50) {
					Message message = new Message( );
					message.what = HANDLER_SHOW_TOAST;
					message.obj = "不能获取 TS 通道！";
					handlerHolder.sendMessage(message);
				}
            }
		}

		mFBlock.PlayMode.Set(true);
		if (m_TsReceiver != null) {
			m_TsReceiver.SetPause(true);
			mFBlock.TimePosition.Set(mTotalTime>5?((mTotalTime-3)*1000/mTotalTime):0);
			mFBlock.PlayMode.Set(false);
			m_TsReceiver.SetPause(false);
			new Thread() {
				public void run() {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Message message = new Message( );
					message.what = HANDLER_MEDIA_PLAY;
					handlerHolder.sendMessage(message);
				}
			}.start();
		}
	}

	private int mProgressHold = 0;
	private synchronized void Play(){
		if (mQRCodeLay.getVisibility() == View.VISIBLE) {
			hideQRCode();
		}
		if (m_TsReceiver != null) {
			m_TsReceiver.SetPause(true);
		}
		mPlayDuration = 0;
		mTotalTime = mTruck.getPlayInfo().getTotalTime();
		Log.e("","====== mTotalTime = "+mTotalTime);

		FBlock.ResetStatus();

		Variables.isPaid = DataHelper.getIsPayment(mContext, mTruck.getCategoryId(), mTruck.getCategorySubId(), mTruck.getMediaInfo().getTruckMeta().getTruckUuid());
		String truckFileName = mTruck.getMediaInfo().getTruckMeta().getTruckFilename();
		String mediaName = mTruck.getMediaInfo().getTruckMeta().getTruckTitle();
		if(mTruck.getMediaInfo().getTruckMeta().getTruckMediaType() == Contant.MEDIA_TYPE_MUSIC) {
			if (null != mFBlock) {
				mFBlock.SelectFile.Set(Contant.ContentType.audio, truckFileName+".ts", "");
			}

			String lrcPath = Contant.getTruckMetaNodePath(mTruck.getMediaInfo().getCategoryId(),mTruck.getMediaInfo().getCategorySubId(),truckFileName,true);
			getLrcDisplay(lrcPath+"/"+truckFileName+".LRC");
			String imgPath = Contant.getTruckMetaNodePath(mTruck.getMediaInfo().getCategoryId(),mTruck.getMediaInfo().getCategorySubId(),mTruck.getMediaInfo().getTruckMeta().getTruckFilename(),true);
			String imgName = mTruck.getMediaInfo().getTruckMeta().getTruckImage();
			String[] truckDesc = mTruck.getMediaInfo().getTruckMeta().getTruckDesc().split("&&");
			if(new File(imgPath).exists()){
				musiccover.setImageURI(Uri.parse(imgPath+"/"+imgName));
				musiccover_full.setImageURI(Uri.parse(imgPath+"/"+imgName));
			}
			if(mTruck.getMediaInfo().getTruckMeta().getTruckShow() == Variables.TRUCK_SHOW_NORMAL) {
				musicname.setText(mediaName);
				musicsinger.setText(truckDesc.length>0?truckDesc[0]:"");
				musicalbum.setText(truckDesc.length>1?truckDesc[1]:"");
			}else {
				musicname_full.setText(mediaName);
				musicsinger_full.setText(truckDesc.length>0?truckDesc[0]:"");
				musicalbum_full.setText(truckDesc.length>1?truckDesc[1]:"");
			}
		}
		else if (null != mFBlock) {
			mFBlock.SelectFile.Set(Contant.ContentType.video, truckFileName+".ts", "");
		}

		String mStr = getDurationString(0);
		mDurationTextView.setText(mStr);
		mTotalTimeTextView.setText(getDurationString(mTotalTime));

		int progress = mProgressHold;
		mProgressHold = 0;
		if(progress != 0){
			if(mTruck.getMediaInfo().getTruckMeta().getTruckMediaType() == Contant.MEDIA_TYPE_MUSIC) {
				CurrentTime = mTotalTime*1000*progress/100;
				updateLrc();
			}
			try {
				Thread.sleep(350);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		mPlayDuration = (progress*mTotalTime)/100;
		mSeekBar.setProgress(progress);
		mFBlock.TimePosition.Set(progress * 10);
		if (mStop) {
			mPlayButton.performClick();
		} else {
			if (m_TsReceiver != null) {
				m_TsReceiver.SetPause(false);
			}

		}
		mFBlock.PlayStatus.Get();

		if (!mTruck.getPayInfo().getPayType() || Variables.isPaid) {
			mPaidTipsLay.setVisibility(View.GONE);
		} else {
			mPaidPrice.setText(" "+mTruck.getPayInfo().getPayFreeTime()+" ");
			mPaidTipsLay.setVisibility(View.VISIBLE);
		}
	}

	private void playAds() {
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mTopCount = mTxtCount = mCrossCount = 0;
				mWindowTopList = getWindowList(Contant.ADS_WINDOW_ORIENT_TOP);

				LoadTextView(mWindowTxtList);

				if( CrossBitMapArray.size() != 0 )
					mCrossImg.setImageBitmap(CrossBitMapArray.get(mCrossCount));

				if( mWindowTopList.size() != 0){
					mWinAdsPlayTime = System.currentTimeMillis();
					TruckMediaProtos.CTruckMediaNode truck = mWindowTopList.get(mTopCount);
					String path = Contant.getTruckMetaNodePath(truck.getMediaInfo().getCategoryId(),truck.getMediaInfo().getCategorySubId(), truck.getMediaInfo().getTruckMeta().getTruckFilename()+"/"+truck.getMediaInfo().getTruckMeta().getTruckFilename()+".mp4",true);
					mTopVideo.stopPlayback();
					mTopVideo.setVideoURI(Uri.parse(path));
					mTopVideo.start();
				}
			}
		});
	}

	private List<TruckMediaProtos.CTruckMediaNode> getWindowList(int orient) {
		return GDApplication.getmInstance().getTruckMedia().getcAds().getWindowOrientTrucksMap(orient);
	}

	private String getDurationString(int counter){
		String mStr = "";
		if(counter >= 0){
				mStr = getResources().getString(R.string.time_string_movie, counter/3600,(counter%3600)/60, ((counter%3600)%60)%60);
		}
		return mStr;
	}

	@Override
	public void onBackPressed() {
		if (mQRCodeLay.getVisibility() == View.VISIBLE) {
			hideQRCode();
		} else {
			super.onBackPressed();
			if(mFBlock != null) mFBlock.TimePosition.Set(1000);
			ExitActivity();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		playAds();
		register();
		isOnResumeRestart = true;
		isSurfaceChanged = false;
		isInitMediaPlayer = false;
		InitMediaListView();
		isPauseActivity = false;
	}

	@Override
	protected void onPause() {
		super.onPause();
		Variables.isPaid = false;
		PauseActivity();
		unregisterReceiver(receiver);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		CancelDialog();
		timer.cancel();
		Variables.isPaid = false;
		onDemandRecording(false, Contant.PROPERTY_STATISTICS_MEDIA_ID, mTruck, mContext);
	}

	private void hideMovieList(){
		mLinearLayout.setVisibility(View.GONE);
		hideBar();
	}

	private void hideBar() {
		handlerRemoveCallbacks();
		handler = new Handler();
		handler.postDelayed(runnable = new Runnable() {
			public void run() {
				if (lilplaybar.getVisibility() == View.VISIBLE) {
					if(isWidescreen){
						Animations.playbarHiddenWide(lilplaybar, SHOW_TIME);
					}else{
						Animations.playbarHiddenNormal(lilplaybar, SHOW_TIME);
					}
					lilplaybar.setVisibility(View.GONE);
				}
				if (mLinearLayout.getVisibility() == View.VISIBLE) {
					if(isWidescreen){
						Animations.movielistHidden(mLinearLayout, SHOW_TIME, 260);
					} else {
						Animations.movielistHidden(mLinearLayout, SHOW_TIME, isFullScreen?278:208);
					}
				}
			}
		}, DELAY_TIME);
	}

	private void handlerRemoveCallbacks() {
		if(handler != null ) handler.removeCallbacks(runnable);
	}

	private void InitMediaListView() {
		this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				List<TruckMediaProtos.CTruckMediaNode> trucks = new ArrayList<>();
				switch (classId) {
					case Contant.CATEGORY_MEDIA_ID:// 多媒体
						if (((PlayGroupMode)mGroupAdapter.getSelectItem()).classSubId != -1) { // 取大类 Media 的某子类的的Movie类别
							trucks = Variables.mTruckMediaMovieNodes.get(((PlayGroupMode)mGroupAdapter.getSelectItem()).classSubId);
						} else {// 大类 Media 各子类中 Type描述类
							trucks = Variables.mTruckMediaTypeNodes.get(((PlayGroupMode)mGroupAdapter.getSelectItem()).classMainId-1);
						}
						break;

					case Contant.CATEGORY_GOLDING_ID:// JTV
						trucks = GDApplication.getmInstance().getTruckMedia().getcGoldingMedia().getmJTVtruckMediaNodes();
						break;

					case Contant.CATEGORY_HOTZONE_ID:// 热门推荐类
						trucks = GDApplication.getmInstance().getTruckMedia().getcHotZone().getTruckMediaNodes();
						break;

					default:// 其他：
						break;
				}

				//cardList 排序"时间、名称"

				mMediaAdapter.notifyDataSetChanged(trucks, mMediaAdapter.getSelectPosition());

				if (trucks != null && trucks.size() != 0) {
					Log.i("", "-----trucks.size() = "+trucks.size());
				}
				if (isOnResumeRestart) {
					if (trucks != null && trucks.size() != 0) {
						mGroupAdapter.setPlayGroup();
						mMediaAdapter.setTrucksToPlsyTrucks();
						if (mTruck == null) {
							mTruck = trucks.get(position);
							new Thread(new Runnable() {
								@Override
								public void run() {
									if(isOnCreate) onDemandRecording(true, Contant.PROPERTY_STATISTICS_MEDIA_ID, mTruck, mContext);
								}
							}).start();
						}
						isOnCreate = false;
						Message message = new Message();
						message.what = HANDLER_UPDATE_MEDIA_INFO;
						handlerHolder.sendMessage(message);
					}
				} else {
					if (mTruck != null)
					{
						Message message = new Message();
						message.what = HANDLER_MEDIA_LIST_SELECT;
						if (mGroupAdapter.getPlayGroup().groupId != ((PlayGroupMode)mGroupAdapter.getSelectItem()).groupId) {
							message.obj = 0;
						} else {
							message.obj = mMediaAdapter.getSelectPosition();
						}
						handlerHolder.sendMessage(message);
					}
				}
			}
		});
	}

	private void updateLrc() {
		if(mTruck.getMediaInfo().getTruckMeta().getTruckShow() == Variables.TRUCK_SHOW_NORMAL) {
			musiclrc.SetIndex(LrcIndex());
			musiclrc.invalidate();
		} else {
			musiclrc_full.SetIndex(LrcIndex());
			musiclrc_full.invalidate();
		}
	}

	private void getLrcDisplay(String path){
		LrcProcess mLrcProcess = new LrcProcess();
		File f = new File(path);
		musiclrc.setVisibility(View.VISIBLE);
		musicnolrc.setVisibility(View.GONE);
		musiclrc_full.setVisibility(View.VISIBLE);
		musicnolrc_full.setVisibility(View.GONE);
		if(!f.exists()){
			musiclrc.setVisibility(View.GONE);
			musicnolrc.setVisibility(View.VISIBLE);
			musiclrc_full.setVisibility(View.GONE);
			musicnolrc_full.setVisibility(View.VISIBLE);
			//return;
		}else{
			CurrentTime = 1000;
			mLrcProcess.readLRC(path);
			lrcList.clear();
			lrcList = mLrcProcess.getLrcContent();
			musiclrc.setSentenceEntities(lrcList,isWidescreen,isFullScreen);
			musiclrc.setAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha_z));
			musiclrc_full.setSentenceEntities(lrcList,isWidescreen,isFullScreen);
			musiclrc_full.setAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha_z));
		}
	}

	private int LrcIndex() {
		int CountTime = mTotalTime*1000;
		CurrentTime += 200;
		if (CurrentTime < CountTime) {
			for (int i = 0; i < lrcList.size(); i++) {
				if (i < lrcList.size() - 1) {
					int iTime = lrcList.get(i).getLrc_time();
					int nTime = lrcList.get(i + 1).getLrc_time();
					if (CurrentTime <= iTime && i == 0) {
						index = i;
					}
					if (CurrentTime >= iTime && CurrentTime < nTime) {
						if(index != i){
							index = i;
						}
					}
				}

				if (i == lrcList.size() - 1	&& CurrentTime > lrcList.get(i).getLrc_time()) {
					index = i;

				}
			}
		}
		return index;
	}

	//组标题列表适配器
	private class GroupAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		private ArrayList<PlayGroupMode> list;
		private PlayGroupMode mPlayGroup;
		private int selectPosition = 0;
		public GroupAdapter(Context context, ArrayList<PlayGroupMode> groupModeList) {
			mContext = context;
			mInflater = LayoutInflater.from(context);
			list = groupModeList;
		}

		public void setSelectPosition(int selectPosition) {
			this.selectPosition = selectPosition;
		}

		public Object getSelectItem() {
			return getItem(selectPosition);
		}

		public void setPlayGroup() {
			mPlayGroup = list.get(selectPosition);
		}

		public PlayGroupMode getPlayGroup() {
			return mPlayGroup;
		}

		public int getCount() {
			return list.size();
		}

		public Object getItem(int position) {
			return list.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.group_list_item, parent,false);
				holder = new ViewHolder();
				holder.text = (TextView) convertView.findViewById(R.id.groupnametext);
				holder.imageView =(ImageView)convertView.findViewById(R.id.grouptips);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			if (position == selectPosition) {
				holder.text.setSelected(true);
				holder.imageView.setImageResource(R.mipmap.play_group_tip_checked);
				convertView.setBackgroundResource(R.mipmap.play_listitem_checked);
			} else {
				holder.text.setSelected(false);
				holder.imageView.setImageResource(R.mipmap.play_group_tip_normal);
				convertView.setBackgroundResource(R.color.transparent);
			}

			holder.text.setText(list.get(position).name);
			return convertView;
		}

		class ViewHolder {
			TextView text;
			ImageView imageView;
		}
	}

	//媒体列表适配器
	private  class MediaAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		List<TruckMediaProtos.CTruckMediaNode> mTrucks = new ArrayList<>();
		List<TruckMediaProtos.CTruckMediaNode> mPlayTrucks = new ArrayList<>();
		private int selectPosition = 0;
		private long selectedIndexId = 0;

		public MediaAdapter(Context context) {
			mInflater = LayoutInflater.from(context);
		}

		public void notifyDataSetChanged(List<TruckMediaProtos.CTruckMediaNode> trucks, int position) {
			this.selectPosition = position;
			if (trucks != null) {
				this.mTrucks = trucks;
			}
			notifyDataSetChanged();
		}

		public void setTrucksToPlsyTrucks() {
			mPlayTrucks = mTrucks;
		}

		public void setSelectPosition(int selectPosition) {
			if(selectPosition >= getPlayTrucksCount()) {
				selectPosition = 0;
			} else if (selectPosition < 0) {
				selectPosition = getPlayTrucksCount() - 1;
			}
			this.selectPosition = selectPosition;
		}

		public void setSelectPositionFirst(int selectPosition) {
			this.selectPosition = selectPosition;
		}

		public int getSelectPosition() {
			return selectPosition;
		}

		public Object getSelectItem() {
			return getItem(selectPosition);
		}

		public long getSelectedItemId() {
			return selectedIndexId;
		}

		public int getPlayTrucksCount() {
			return mPlayTrucks.size();
		}

		public int getCount() {
			return mTrucks.size();
		}

		public Object getList(int position) {
			return mTrucks;
		}

		public Object getItem(int position) {
			if (mPlayTrucks == null) return null;
			else if (position >= mPlayTrucks.size()) return null;
			return mPlayTrucks.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.media_list_item, parent,false);
				holder = new ViewHolder();
				holder.text = (MTextView) convertView.findViewById(R.id.medianametext);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			TruckMediaProtos.CTruckMediaNode node = mTrucks.get(position);
			holder.text.setText(node.getMediaInfo().getTruckMeta().getTruckTitle());

			if (position == selectPosition && mGroupAdapter.getPlayGroup().groupId == ((PlayGroupMode)mGroupAdapter.getSelectItem()).groupId) {
				convertView.setBackgroundResource(R.mipmap.play_listitem_checked);
				holder.text.setSelected(true);
			} else {
				convertView.setBackgroundResource(R.color.transparent);
				holder.text.setSelected(false);
			}

			return convertView;
		}

		class ViewHolder {
			MTextView text;
			ImageView imageView;
		}
	}

	private AlertDialog mDialog = null;
	private void ShowDialog() {
		if (mDialog == null) {
			AlertDialog.Builder b = new AlertDialog.Builder(mContext);
			mDialog = b.create();
			mDialog.setCancelable(true);
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = inflater.inflate(R.layout.seekbar_dialog_wide, null);
			LayoutParams params = mDialog.getWindow().getAttributes();
			if(params != null){
				params.x = LayoutParams.MATCH_PARENT;
				params.y = LayoutParams.MATCH_PARENT;
				mDialog.getWindow().setAttributes(params);
			}

			mDialog.setView(view, 0, -50, 0, 0);
			SeekBar mSeek = (SeekBar)view.findViewById(R.id.seekbar);

			int data  = Utils.getScreenBrightness(this);
			mSeek.setProgress(data*100/255);
			Log.e("","<== dialog ==> " + mSeek.getProgress());

			mSeek.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
				}

				@Override
				public void onProgressChanged(SeekBar seekBar, int progress,
											  boolean fromUser) {
					Log.e("","<==progress==> "+progress+" / "+fromUser);

					int bt = 255*progress / 100;
					Utils.setBrightness(MediaPlayActivity.this,bt);
				}
			});
		}
		mDialog.show();
	}

	@SuppressWarnings("ResourceType")
	private void ShowDialog(boolean isWide) {
		if (mDialog == null) {
			AlertDialog.Builder b = new AlertDialog.Builder(mContext);
			mDialog = b.create();
			mDialog.setCancelable(true);
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View view = inflater.inflate(isWide?R.layout.eq_dialog_wide
					:R.layout.eq_dialog_normal, null);
			LayoutParams params = mDialog.getWindow().getAttributes();
			if(params != null){
				params.x = LayoutParams.MATCH_PARENT;
				params.y = LayoutParams.MATCH_PARENT;
				mDialog.getWindow().setAttributes(params);
			}

			mDialog.setView(view, 0, 0, 0, 0);

			mEqText1 = (TextView)view.findViewById(R.id.text100);
			mEqText2 = (TextView)view.findViewById(R.id.text500);
			mEqText3 = (TextView)view.findViewById(R.id.text1000);
			mEqText4 = (TextView)view.findViewById(R.id.text4000);
			mEqText5 = (TextView)view.findViewById(R.id.text16k);
			mEqText1.setText("0");
			mEqText2.setText("0");
			mEqText3.setText("0");
			mEqText4.setText("0");
			mEqText5.setText("0");


			SeekBar mSeek100 = (SeekBar)view.findViewById(R.id.seekbar1);
			mSeek100.setOnSeekBarChangeListener(seekbarChangeListener);
			mSeek100.setId(1);
			mSeek100.setProgress(10);

			SeekBar mSeek500 = (SeekBar)view.findViewById(R.id.seekbar2);
			mSeek500.setOnSeekBarChangeListener(seekbarChangeListener);
			mSeek500.setProgress(10);
			mSeek500.setId(2);

			SeekBar mSeek1k = (SeekBar)view.findViewById(R.id.seekbar3);
			mSeek1k.setOnSeekBarChangeListener(seekbarChangeListener);
			mSeek1k.setProgress(10);
			mSeek1k.setId(3);

			SeekBar mSeek4k = (SeekBar)view.findViewById(R.id.seekbar4);
			mSeek4k.setOnSeekBarChangeListener(seekbarChangeListener);
			mSeek4k.setProgress(10);
			mSeek4k.setId(4);

			SeekBar mSeek16k = (SeekBar)view.findViewById(R.id.seekbar5);
			mSeek16k.setOnSeekBarChangeListener(seekbarChangeListener);
			mSeek16k.setProgress(10);
			mSeek16k.setId(5);
		}
		mDialog.show();
	}

	private void CancelDialog() {
		if (mDialog != null) {
			if (mDialog.isShowing()) {
				mDialog.cancel();
				mDialog = null;
			}
		}
	}

	private Boolean mRepeat = false;
	private void startWindowAdActivity(int position, int orient) {
		if (mRepeat) return;
		mRepeat = true;
		if (getWindowList(orient) != null && getWindowList(orient).size() > 0) {

			TruckMediaProtos.CTruckMediaNode truck = GDApplication.getmInstance().getTruckMedia().getcAds().getWindowOrientTrucksMap(orient).get(position);
			Intent mIntent ;
			switch (truck.getCategorySubId()) {
				case Contant.PROPERTY_ADS_MEDIA_ID:
					mIntent = new Intent(mContext, WindowAdsPlayActivity.class);
					break;

				case Contant.PROPERTY_ADS_IMG_ID:
					mIntent = new Intent(mContext, JmagazineActivity.class);
					break;
				default:
					return;
			}
			mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			mIntent.putExtra("position", position);
			mIntent.putExtra("orient", orient);
			mIntent.putExtra("truck", truck);
			mContext.startActivity(mIntent);
		} else {
			return;
		}
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mRepeat = false;
			}
		}).start();
	}

	private void register(){
		IntentFilter filter = new IntentFilter();
		filter.addAction(Contant.Actions.CLOSEACTIVITY);
		filter.addAction(Contant.Actions.PLAY_STATUS_REPORT);
		filter.addAction("com.golding.start.playads");
		filter.addAction("com.marqueetextView.start");
		filter.addAction("com.marqueetextView.new");
		filter.addAction("com.marqueetextView.gone");
		filter.addAction("com.golding.nullTsExit");
		filter.addAction("com.golding.isWindowUpdate");
		filter.addAction("com.golding.getIsPayment");
		registerReceiver(receiver, filter);
	}

	private BroadcastReceiver receiver = new BroadcastReceiver() {
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			Log.e("","-----MediaPlayActivity action = "+action);
			if("com.golding.nullTsExit".equals(action)){
				nullTsExitActivity();
			} else if(Contant.Actions.PLAY_STATUS_REPORT.equals(action)){
				int status = intent.getIntExtra("status", -2);
				Log.e("","===== play status = "+status);
				switch(status){
					case Contant.StatusCode.PLAY_STATUS_FAILURE_NO_SDCARD:
						ExitActivity();
						break;

					case Contant.StatusCode.PLAY_STATUS_FAILURE_NO_FILE:
						Toast.makeText(mContext,
								mContext.getResources().getString(R.string.nofile),
								Toast.LENGTH_LONG).show();
					case Contant.StatusCode.PLAY_STATUS_INVALID:
					case Contant.StatusCode.PLAY_STATUS_FAILURE:
					case Contant.StatusCode.PLAY_STATUS_FILEEND:
						if (!fileEnd) {
							Log.i("", "--FILEEND--PlayNextFile");
							PlayNextFile();
						}
						break;
					case Contant.StatusCode.PLAY_STATUS_SUCCESS:
						mStop = false;
					default:
						break;
				}
			} else if("com.marqueetextView.start".equals(action) ||
					"com.marqueetextView.new".equals(action)){
				mFlipperLay.setVisibility(View.GONE);

			} else if("com.marqueetextView.gone".equals(action)){
				try {
					mWindowTxtList  = GDApplication.getmInstance().getTruckMedia().getcAds().getExtendTypeTrucksMap(Contant.ADS_EXTEND_TYPE_MGR);
					LoadTextView(mWindowTxtList);
					mFlipperLay.setVisibility(View.VISIBLE);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if("com.marqueetextView.isWindowUpdate".equals(action)){
				isWindowUpdate = intent.getBooleanExtra("isWindowUpdate", false);
				if (isWindowUpdate) {
					mWindowTopList = new ArrayList<>();
				}
			} else if("com.golding.getIsPayment".equals(action)){// 收到付款消息 或 付款有效期结束消息
				if (!Variables.isPaid) {
					Variables.isPaid = DataHelper.getIsPayment(mContext, mTruck.getCategoryId(), mTruck.getCategorySubId(), mTruck.getMediaInfo().getTruckMeta().getTruckUuid());
					if (Variables.isPaid) {
						hideQRCode();
						mPaidTipsLay.setVisibility(View.GONE);
						if (mStop) {
							int payFreeTime = mTruck.getPayInfo().getPayFreeTime() * 60;
							if(mFBlock != null && mTotalTime > payFreeTime) {
								mPlayDuration = payFreeTime;
								mSeekBar.setProgress(mPlayDuration * 100 / mTotalTime);
								mFBlock.TimePosition.Set(mPlayDuration * 1000 / mTotalTime);
							}
							mPlayButton.performClick();
						}
					}
				} else {
					Variables.isPaid = DataHelper.getIsPayment(mContext, mTruck.getCategoryId(), mTruck.getCategorySubId(), mTruck.getMediaInfo().getTruckMeta().getTruckUuid());
					if (!Variables.isPaid) {
						mPaidTipsLay.setVisibility(View.VISIBLE);
						//订单过期
					}
				}
			}
		}
	};

	@Override
	public void handlerMessage(Message msg) {
		switch (msg.what) {
			case HANDLER_TIMER:
				// 如果不是在Media点播界面
				if(isPauseActivity) return;
				mTimerCounter++;

				// 监听媒体播放
				if(!isPauseActivity && !mStop){
					if(mPlayDuration < mTotalTime){
						mPlayDuration++;
						String mStr = getDurationString(mPlayDuration);
						mDurationTextView.setText(mStr);
						mSeekBar.setProgress(mPlayDuration*100/mTotalTime);
					} else if(mTotalTime != 0){
						mPlayDuration = 0;
						Log.i("", "-----mMsgHandler PlayNextFile");
						fileEnd = true;
						PlayNextFile();

						new Thread(new Runnable() {
							@Override
							public void run() {
								try {
									Thread.sleep(2000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								fileEnd = false;
							}
						}).start();
					}

					boolean payType;
					int payFreeTime;
					payType = mTruck.getPayInfo().getPayType();
					payFreeTime = mTruck.getPayInfo().getPayFreeTime();
					if (!payType || Variables.isPaid || mPlayDuration/60 < payFreeTime) {
					} else {
						if (!mStop && m_TsReceiver != null) {
							mStop = true;
							mFBlock.PlayMode.Set(mStop);
							m_TsReceiver.SetPause(mStop);
							mPlayButton.setBackgroundResource(mStop?R.drawable.pausedbtn_style:R.drawable.playedbtn_style);
							showQRCode();
						}
					}
				}

				// 监听窗口广告
				if(mTimerCounter % 20 == 0 && !isWindowUpdate){
					FlashAdMsg();
				}

				// 3秒更新点一次播时间
				if(mTimerCounter % 3 == 0){
					DataHelper.updateStatistics(mContext, 3000, mTruck, Contant.PROPERTY_STATISTICS_MEDIA_ID);
				}

				// 5分钟刷新一次二维码
				if (timeQRCode>300 && mQRCodeLay.getVisibility() == View.VISIBLE) {
					timeQRCode = 0;
					hideQRCode();
					showQRCode();
				}
				timeQRCode++;
				break;

			case HANDLER_MEDIA_PLAY:
				if (m_TsReceiver != null && mFBlock != null && !isPauseActivity)
					if(mTruck != null) Play();
				break;

			case HANDLER_SHOW_CODEVIEW:
				String codeUrl = (String)msg.obj;
				mStatusMsg.setVisibility(View.GONE);
				mCodeView.setVisibility(View.VISIBLE);
				mCodeView.loadUrl(codeUrl);
				mCodeView.setInitialScale(180);
				mCodeView.reload();
				break;

			case HANDLER_STATUS_MSG:
				mStatusMsg.setText((String)msg.obj);
				break;

			case HANDLER_ADAPTER_VIEW:
				break;

			case HANDLER_UPDATE_MEDIA_INFO:
				if (isSurfaceChanged && !isOnResumeRestart && !isSurfaceMediaPlayer)
					InitMediaPlayer();
				isSurfaceMediaPlayer = false;
				break;

			case HANDLER_INIT_MEDIAPLAYER2:
				InitMediaPlayer(false);
				break;

			case HANDLER_SHOW_TOAST:
				NToast.shortToast(mContext, (String)msg.obj);
				break;

			case HANDLER_MEDIA_LIST_SELECT:
				int position = (int)msg.obj;
				Log.i("", "-----mMsgHandler 9 position = "+position);
				mMediaListView.setSelection(position);
				break;

			case HANDLER_PLAY_LRC:
				if(!isPauseActivity && mTruck.getMediaInfo().getTruckMeta().getTruckMediaType() == Contant.MEDIA_TYPE_MUSIC) {
					updateLrc();
					handlerHolder.sendEmptyMessageDelayed(HANDLER_PLAY_LRC, 200);
				}
				break;
		}
	}

	private long timeQRCode = 0;
	private void showQRCode() {
		Log.i("", "-----showQRCode()1");
		if (mQRCodeLay.getVisibility() != View.VISIBLE) {
			mStatusMsg.setText(mContext.getResources().getString(R.string.getting_qrcode));
			mQRCodeLay.setVisibility(View.VISIBLE);
			mStatusMsg.setVisibility(View.VISIBLE);
			mCodeView.setVisibility(View.GONE);
			new Thread(new Runnable() {
				@Override
				public void run() {
					Log.i("", "-----showQRCode()2");
					try {
						// 查找是否有 本商品的 未过期 二维码
						if (mTruck == null) return;
						Order orderExistent = DataHelper.getQRCodeUrl(mContext, mTruck.getCategoryId(), mTruck.getCategorySubId(), mTruck.getMediaInfo().getTruckMeta().getTruckUuid());
						if (orderExistent != null) {// 有就显示出来
							Message message = new Message();
							message.what = HANDLER_SHOW_CODEVIEW;
							message.obj = orderExistent.qrcodeUrl;
							handlerHolder.sendMessage(message);
							timeQRCode = orderExistent.count/1000;
						} else {// 没有就HTTP请求
							HashMap<String, String> map = HtmlRequest.getHtmlResult(
									Contant.PAY_HOST + "/orderBusiness/createRcode?" +
											"title=" + URLEncoder.encode(mTruck.getMediaInfo().getTruckMeta().getTruckTitle(), "utf-8") +
											"&money=" + (mTruck.getPayInfo().getPayValue()) +
											"&fileName=" + mTruck.getMediaInfo().getTruckMeta().getTruckFilename() + ".ts" +
											"&fileUUID=" + mTruck.getMediaInfo().getTruckMeta().getTruckUuid() +
											"&masterId=" + MasterId +
											"&deviceId=" + Utils.getSerialID() +
											"&position=" + URLEncoder.encode(Variables.mGpsPlace, "utf-8"));
							String mStatusMsgText = "";
							if (map != null) {
								String netStatus = map.get("netStatus");
								if ("s1".equals(netStatus)) {
									Message message = new Message();
									message.what = HANDLER_SHOW_CODEVIEW;
									message.obj = map.get("qrcode");
									handlerHolder.sendMessage(message);


									Order order = new Order();
									order.ordersn = map.get("ordersn");
									order.classId = mTruck.getCategoryId();
									order.classSubId = mTruck.getCategorySubId();
									order.cardUuid = mTruck.getMediaInfo().getTruckMeta().getTruckUuid();
									order.count = 0;
									order.time = 0;
									order.status = "0";
									order.qrcodeUrl = map.get("qrcode");
									DataHelper.insertMediaOrder(mContext, order);
									timeQRCode = 0;
								} else if ("s0".equals(netStatus)) {
									timeQRCode = 295;
									mStatusMsgText = mContext.getResources().getString(R.string.qrcode_err);
								} else {
									timeQRCode = 295;
									mStatusMsgText = mContext.getResources().getString(R.string.disconnect_to_server)+" "+netStatus;
								}
							} else {
								//网络异常
								timeQRCode = 295;
								mStatusMsgText = mContext.getResources().getString(R.string.network_anomaly);
							}

							if (!"".equals(mStatusMsgText)) {
								Message message = new Message();
								message.what = HANDLER_STATUS_MSG;
								message.obj = mStatusMsgText;
								handlerHolder.sendMessage(message);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

	private void hideQRCode() {
		mQRCodeLay.setVisibility(View.GONE);
	}

	private synchronized void nullTsExitActivity(){
		if (!mStop) {
			mPlayButton.performClick();
		}
		mFBlock.StopStram.Set();
		if ( null != m_TsReceiver ) {
			m_TsReceiver.SetPause(true);
			new Thread(new Runnable() {
				@Override
				public void run() {
					mProgressHold = mProgress;
					Variables.tsStopAllowPlay = false;
					m_TsReceiver.Stop();
					m_TsReceiver = null;
					Variables.isTsStop = true;
					IP.GetLocalIpData(payloadMac);
					int mCount = payloadMac[payloadMac.length-1];
					if(mCount > 50){
						mCount = 50;
					}
					try {
						Thread.sleep(3000+mCount*250);
					} catch (Exception e) {
						e.printStackTrace();
					}
					mFBlock = FBlock.GetInstance();
					m_TsReceiver = new TsReceiver(GlobalSettings.GetAudioSink(), GlobalSettings.GetSource());
					for (int j = 0; j < 50; j++) {
						if (isPauseActivity) break;
						if (mFBlock.MediaPid.Get()) {
							if ( !m_TsReceiver.GetIsRunning() )
								if(mTruck.getMediaInfo().getTruckMeta().getTruckMediaType() == Contant.MEDIA_TYPE_MUSIC)
									m_TsReceiver.Start(
											null,
											mFBlock.MediaPid.GetAudioPid(),
											0xFFFF);
								else
									m_TsReceiver.Start(
											mMovieView.getHolder().getSurface(),
											mFBlock.MediaPid.GetAudioPid(),
											mFBlock.MediaPid.GetVideoPid());
						} else {
							try {
								Thread.sleep(100);
							} catch (InterruptedException ignored) {
							}
						}
					}

					Message message = new Message( );
					message.what = HANDLER_MEDIA_PLAY;
					handlerHolder.sendMessage(message);
				}
			}).start();
		}

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Variables.tsStopAllowPlay = true;
		if ( null != m_TsReceiver ) {
			Variables.isTsStop = false;
			m_TsReceiver.Close();
		}
	}

	private void PauseActivity() {
		if (isPauseActivity) return;
		isPauseActivity = true;
		mProgressHold = mProgress;
		mTopVideo.stopPlayback();
		if ( null != mFBlock ) {
			mFBlock.StopStram.Set();
			mFBlock  = null;
		}

		if ( null != m_TsReceiver ) {
			m_TsReceiver.SetPause(true);
			m_TsReceiver.Stop();
			m_TsReceiver  = null;
		}
	}

	private void ExitActivity() {
		Log.i("","-----MediaPlayActivity ExitActivity ");
		PauseActivity();
		finish();
	}

	private void LoadTextView(List<TruckMediaProtos.CTruckMediaNode> textDataList){
		NLog.d("MediaPlayActivity","mTxtSize:"+textDataList.size()+" mTxtCount:"+mTxtCount);
		if(textDataList.size() == 0){
			return;
		}
		if (Variables.isMarqueeShow) {
			return;
		}
		mFlipper.setText(textDataList.get(mTxtCount).getMediaInfo().getTruckMeta().getTruckDesc());
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		switch (v.getId()) {

			case R.id.videotop:
				startWindowAdActivity(mTopCount, Contant.ADS_WINDOW_ORIENT_TOP);
				break;

			case R.id.imgmid:
				//startWindowAdActivity(mMiddleCount, Contant.ADS_WINDOW_ORIENT_MIDDLE);
				break;

			case R.id.imgbottom:
				//startWindowAdActivity(mBottomCount, Contant.ADS_WINDOW_ORIENT_BOTTOM);
				break;

			case R.id.paid_tips_lay:
				showQRCode();
				break;
		}
		return false;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.cross:
				if (mRepeat) return;
				mRepeat = true;
				if (CrossBitMapArray != null && CrossBitMapArray.size() > 0) {
					ExitActivity();
					TruckMediaProtos.CTruckMediaNode truck = getWindowList(Contant.ADS_WINDOW_ORIENT_CROSSBAR).get(mCrossCount);
					String path = Contant.getTruckMetaNodePath(truck.getMediaInfo().getCategoryId(),truck.getMediaInfo().getCategorySubId(), truck.getMediaInfo().getTruckMeta().getTruckFilename()+".jpg",true);
					Intent mIntent = new Intent(mContext, ImageActivity.class);
					mIntent.putExtra("type", 1);
					mIntent.putExtra("filename", path);
					mContext.startActivity(mIntent);
				}
				break;

			case R.id.lilplaybar:
				hideMovieList();
				break;

			case R.id.btnprevious:
				PlayPreviousFile();
				hideBar();
				break;

			case R.id.btnnext:
				PlayNextFile();
				hideBar();
				break;

			case R.id.btnstop:
				if(firstStop) {
					firstStop = false;
				} else {
					hideMovieList();
				}
				boolean payType;
				int payFreeTime;
				payType = mTruck.getPayInfo().getPayType();
				payFreeTime = mTruck.getPayInfo().getPayFreeTime();
				if (!mStop || !payType || Variables.isPaid || mPlayDuration/60 < payFreeTime) {
					mStop = !mStop;
					if ( null != mFBlock ) {
						mFBlock.PlayMode.Set(mStop);
					}
					if (m_TsReceiver != null) {
						m_TsReceiver.SetPause(mStop);
					}
					mPlayButton.setBackgroundResource(mStop?R.drawable.pausedbtn_style:R.drawable.playedbtn_style);
				} else {
					showQRCode();
				}
				break;

			case R.id.btnfunc:
				hideMovieList();
				if(mTruck.getMediaInfo().getTruckMeta().getTruckMediaType() == Contant.MEDIA_TYPE_MUSIC)
					ShowDialog(isWidescreen);
				else
					ShowDialog();
				break;

			case R.id.playblack:
				if(mFBlock != null) mFBlock.TimePosition.Set(1000);
				ExitActivity();
				break;
		}
	}

	private class VolumeSeekBarChange  implements OnSeekBarChangeListener {
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			am.setStreamVolume(AudioManager.STREAM_MUSIC,progress, AudioManager.FLAG_PLAY_SOUND);
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
            handlerRemoveCallbacks();
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
            hideBar();
		}
	}

	private class ProgressSeekBarChange implements OnSeekBarChangeListener {

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			if(mTruck.getMediaInfo().getTruckMeta().getTruckMediaType() == Contant.MEDIA_TYPE_MUSIC) {
				CurrentTime = mTotalTime*1000*mProgress/100;
				updateLrc();
			}

			String mStr = getDurationString(mTotalTime*mProgress/100);
			mPlayDuration = mTotalTime*mProgress/100;
			mDurationTextView.setText(mStr);

			boolean payType;
			int payFreeTime;
			payType = mTruck.getPayInfo().getPayType();
			payFreeTime = mTruck.getPayInfo().getPayFreeTime();
			if (!payType || Variables.isPaid || mPlayDuration/60 < payFreeTime) {
				if (mFBlock != null && m_TsReceiver != null) {
					mFBlock.TimePosition.Set(mProgress * 10);//mTotalTime*progress/100);
					mStop = false;
					mFBlock.PlayMode.Set(mStop);
					m_TsReceiver.SetPause(mStop);
					mPlayButton.setBackgroundResource(mStop?R.drawable.pausedbtn_style:R.drawable.playedbtn_style);
					hideQRCode();
					hideBar();
				} else { }
			} else {
				mStop = true;
				mPlayButton.setBackgroundResource(mStop?R.drawable.pausedbtn_style:R.drawable.playedbtn_style);
				showQRCode();
			}
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			if (mFBlock != null && m_TsReceiver != null) {
				mFBlock.PlayMode.Set(true);
				m_TsReceiver.SetPause(true);
			} else {
				InitMediaPlayer();
			}
			if (mLinearLayout.getVisibility() == View.VISIBLE) {
				Animations.movielistHidden(mLinearLayout, SHOW_TIME, 260);
			}
			handlerRemoveCallbacks();
		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
									  boolean fromUser) {
			mProgress = progress;
		}
	}

	private OnSeekBarChangeListener seekbarChangeListener = new OnSeekBarChangeListener() {
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
									  boolean fromUser) {
			int id = seekBar.getId();
			switch(id){
				case 1:
					mEqText1.setText(String.valueOf(progress-10));
					break;
				case 2:
					mEqText2.setText(String.valueOf(progress-10));
					break;
				case 3:
					mEqText3.setText(String.valueOf(progress-10));
					break;
				case 4:
					mEqText4.setText(String.valueOf(progress-10));
					break;
				case 5:
					mEqText5.setText(String.valueOf(progress-10));
					break;
				default:
					break;
			}
			Log.e("","<==progress==> "+progress+" / "+fromUser);

		}
	};

	@Override
	public void onPrepared(MediaPlayer arg0) {
		arg0.setVolume(0.0f, 0.0f);
	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		arg0.setVolume(0.0f, 0.0f);
		if( mWindowTopList.size() != 0) minWinAdsStatistics();
		if(mTopCount < mWindowTopList.size()-1){
			mTopCount++;
		} else {
			mTopCount = 0;
		}
		if( mWindowTopList.size() != 0){
			TruckMediaProtos.CTruckMediaNode truck = mWindowTopList.get(mTopCount);
			String path = Contant.getTruckMetaNodePath(truck.getMediaInfo().getCategoryId(),truck.getMediaInfo().getCategorySubId(), truck.getMediaInfo().getTruckMeta().getTruckFilename()+"/"+truck.getMediaInfo().getTruckMeta().getTruckFilename()+".mp4",true);
			if (!new File(path).exists()) return;
			mTopVideo.stopPlayback();
			mTopVideo.setVideoURI(Uri.parse(path));
			mTopVideo.start();
		}
	}

	private long mWinAdsPlayTime = 0;
	private void minWinAdsStatistics() {
		TruckMediaProtos.CTruckMediaNode truck = mWindowTopList.get(mTopCount);
		String path = Contant.getTruckMetaNodePath(truck.getMediaInfo().getCategoryId(),truck.getMediaInfo().getCategorySubId(), truck.getMediaInfo().getTruckMeta().getTruckFilename()+"/"+truck.getMediaInfo().getTruckMeta().getTruckFilename()+".mp4",true);
		MediaMetadataRetriever mmr = new MediaMetadataRetriever();
		mmr.setDataSource(path);
		int duration = Integer.parseInt(mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
		long winAdsPlayTimes = System.currentTimeMillis() - mWinAdsPlayTime;
		mWinAdsPlayTime = System.currentTimeMillis();
		if (mWinAdsPlayTime > duration - 1000) {
			Utils.onDemandRecording(truck, mWinAdsPlayTime, duration);
		}
	}
}
