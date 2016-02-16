package lihu.zlm.web.oauth.helper;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信返回状态码
 *
 * @author wuxincheng
 * @date 2015年6月2日 下午3:43:06
 * @version V1.0
 */
public class WechatReponseCode {

	public static final String SYSTEM_BUSY = "-1";
	public static final String REQUEST_SUCCESS = "0";
	
	public static final String TOKEN_ERROR = "40001";
	public static final String TOKEN_ILLEGAL = "40002";
	public static final String OPENID_ILLEGAL = "40003";
	public static final String MEDIA_TYPE_ILLEGAL = "40004";
	public static final String FILE_TYPE_ILLEGAL = "40005";
	public static final String FILE_SIZE_ILLEGAL = "40006";
	public static final String MEDIA_FILE_ID_ILLEGAL = "40007";
	public static final String MSG_TYPE_ILLEGAL = "40008";
	public static final String IMG_SIZE_ILLEGAL = "40009";
	public static final String VOICE_SIZE_ILLEGAL = "40010";
	public static final String VIDEO_SIZE_ILLEGAL = "40011";
	public static final String NAR_IMG_SIZE_ILLEGAL = "40012";
	public static final String APPID_ILLEGAL = "40013";
	public static final String ACCESS_TOKEN_ILLEGAL = "40014";
	public static final String MENU_TYPE_ILLEGAL = "40015";
	public static final String BTN_COUNT_ILLEGAL = "40016";
	public static final String BUTTON_COUNT_ILLEGAL = "40017";
	public static final String BTN_NAME_LENGTH_ILLEGAL = "40018";
	public static final String BTN_KEY_LENGTH_ILLEGAL = "40019";
	public static final String BTN_URL_LENGTH_ILLEGAL = "40020";
	public static final String MENU_VERSION_ILLEGAL = "40021";
	public static final String SUB_MENU_LEVEL_ILLEGAL = "40022";
	public static final String SUB_MENU_BTN_COUNT_ILLEGAL = "40023";
	public static final String SUB_MENU_BTN_TYPE_ILLEGAL = "40024";
	public static final String SUB_MENU_BTN_NAME_LENGTH_ILLEGAL = "40025";
	public static final String SUB_MENU_BTN_KEY_LENGTH_ILLEGAL = "40026";
	public static final String SUB_MENU_BTN_URL_LENGTH_ILLEGAL = "40027";
	public static final String SUB_MENU_USED_ILLEGAL = "40028";
	public static final String OAUTH_CODE_ILLEGAL = "40029";
	public static final String REFRESH_TOKEN_ILLEGAL = "40030";
	public static final String OPENID_LIST_ILLEGAL = "40031";
	public static final String OPENID_LIST_LENGTH_ILLEGAL = "40032";
	public static final String REQUEST_CHARACTER_ILLEGAL = "40033";
	public static final String REQUEST_PARAM_ILLEGAL = "40035";
	public static final String REQUEST_FOMART_ILLEGAL = "40038";
	public static final String URL_LENGTH_ILLEGAL = "40039";
	public static final String GROUP_ID_ILLEGAL = "40050";
	public static final String GROUP_NAME_ILLEGAL = "40051";
	
	public static final String ACCESS_TOKEN_NULL = "41001";
	public static final String APPID_NULL = "41002";
	public static final String REFRESH_TOKEN_NULL = "41003";
	public static final String SECRET_NULL = "41004";
	public static final String MEDIA_FILE_DATA_NULL = "41005";
	public static final String MEDIA_ID_NULL = "41006";
	public static final String SUB_MENU_DATA_NULL = "41007";
	public static final String OAUTH_CODE_NULL = "41008";
	public static final String OPENID_NULL = "41009";
	
	public static final String ACCESS_TOKEN_TIMEOUT = "42001";
	public static final String REFRESH_TOKEN_TIMEOUT = "42002";
	public static final String OAUTH_CODE_TIMEOUT = "42003";
	
	public static final String GET_NEED = "43001";
	public static final String POST_NEED = "43002";
	public static final String HTTPS_NEED = "43003";
	public static final String USER_FOCUS_NEED = "43004";
	public static final String FRIENDSHIP_NEED = "43005";
	
	public static final String MEDIA_FILE_NULL = "44001";
	public static final String POST_DATA_NULL = "44002";
	public static final String GRAP_MSG_NULL = "44003";
	public static final String TEXT_MSG_NULL = "44004";
	
	public static final String MEDIA_FILE_SIZE_OVER_LIMIT = "45001";
	public static final String MSG_OVER_LIMIT = "45002";
	public static final String TITLE_OVER_LIMIT = "45003";
	public static final String CONTENT_OVER_LIMIT = "45004";
	public static final String LINK_OVER_LIMIT = "45005";
	public static final String IMG_LINK_OVER_LIMIT = "45006";
	public static final String VOICE_PLAY_TIME_OVER_LIMIT = "45007";
	public static final String GRAP_MSG_OVER_LIMIT = "45008";
	public static final String REQUEST_OVER_LIMIT = "45009";
	public static final String MENU_COUNT_OVER_LIMIT = "45010";
	public static final String REPLY_TIME_OVER_LIMIT = "45015";
	public static final String SYSTEM_GOURP_MODIFY_LIMIT = "45016";
	public static final String GROUP_NAME_LENGTH_OVER_LIMIT = "45017";
	public static final String GROUP_COUNT_OVER_LIMIT = "45018";
	
	public static final String MEDIA_DATA_NONE = "46001";
	public static final String MENU_VERSION_NONE = "46002";
	public static final String MENU_DATA_NONE = "46003";
	public static final String USER_NONE = "46004";
	
	public static final String PARSE_ERROE = "47001";
	
	public static final String API_UN_AUTH = "48001";
	
	public static final String API_USER_UN_AUTH = "50001";

	public static Map<String, String> RESP_CODE = new HashMap<String, String>();

	static {
		RESP_CODE.put(SYSTEM_BUSY, "系统繁忙");
		RESP_CODE.put(REQUEST_SUCCESS, "请求成功");
		RESP_CODE.put(TOKEN_ERROR, "获取access_token时AppSecret错误，或者access_token无效");
		RESP_CODE.put(TOKEN_ILLEGAL, "不合法的凭证类型");
		RESP_CODE.put(OPENID_ILLEGAL, "不合法的OpenID");
		RESP_CODE.put(MEDIA_TYPE_ILLEGAL, "不合法的媒体文件类型");
		RESP_CODE.put(FILE_TYPE_ILLEGAL, "不合法的文件类型");
		RESP_CODE.put(FILE_SIZE_ILLEGAL, "不合法的文件大小");
		RESP_CODE.put(MEDIA_FILE_ID_ILLEGAL, "不合法的媒体文件id");
		RESP_CODE.put(MSG_TYPE_ILLEGAL, "不合法的消息类型");
		RESP_CODE.put(IMG_SIZE_ILLEGAL, "不合法的图片文件大小");
		RESP_CODE.put(VOICE_SIZE_ILLEGAL, "不合法的语音文件大小");
		RESP_CODE.put(VIDEO_SIZE_ILLEGAL, "不合法的视频文件大小");
		RESP_CODE.put(NAR_IMG_SIZE_ILLEGAL, "不合法的缩略图文件大小");
		RESP_CODE.put(APPID_ILLEGAL, "不合法的APPID");
		RESP_CODE.put(ACCESS_TOKEN_ILLEGAL, "不合法的access_token");
		RESP_CODE.put(MENU_TYPE_ILLEGAL, "不合法的菜单类型");
		RESP_CODE.put(BTN_COUNT_ILLEGAL, "不合法的按钮个数");
		RESP_CODE.put(BUTTON_COUNT_ILLEGAL, "不合法的按钮个数");
		RESP_CODE.put(BTN_NAME_LENGTH_ILLEGAL, "不合法的按钮名字长度");
		RESP_CODE.put(BTN_KEY_LENGTH_ILLEGAL, "不合法的按钮KEY长度");
		RESP_CODE.put(BTN_URL_LENGTH_ILLEGAL, "不合法的按钮URL长度");
		RESP_CODE.put(MENU_VERSION_ILLEGAL, "不合法的菜单版本号");
		RESP_CODE.put(SUB_MENU_LEVEL_ILLEGAL, "不合法的子菜单级数");
		RESP_CODE.put(SUB_MENU_BTN_COUNT_ILLEGAL, "不合法的子菜单按钮个数");
		RESP_CODE.put(SUB_MENU_BTN_TYPE_ILLEGAL, "不合法的子菜单按钮类型");
		RESP_CODE.put(SUB_MENU_BTN_NAME_LENGTH_ILLEGAL, "不合法的子菜单按钮名字长度");
		RESP_CODE.put(SUB_MENU_BTN_KEY_LENGTH_ILLEGAL, "不合法的子菜单按钮KEY长度");
		RESP_CODE.put(SUB_MENU_BTN_URL_LENGTH_ILLEGAL, "不合法的子菜单按钮URL长度");
		RESP_CODE.put(SUB_MENU_USED_ILLEGAL, "不合法的自定义菜单使用用户");
		RESP_CODE.put(OAUTH_CODE_ILLEGAL, "不合法的oauth_code");
		RESP_CODE.put(REFRESH_TOKEN_ILLEGAL, "不合法的refresh_token");
		RESP_CODE.put(OPENID_LIST_ILLEGAL, "不合法的openid列表");
		RESP_CODE.put(OPENID_LIST_LENGTH_ILLEGAL, "不合法的openid列表长度");
		RESP_CODE.put(REQUEST_CHARACTER_ILLEGAL, "不合法的请求字符，不能包含\\uxxxx格式的字符");
		RESP_CODE.put(REQUEST_PARAM_ILLEGAL, "不合法的参数");
		RESP_CODE.put(REQUEST_FOMART_ILLEGAL, "不合法的请求格式");
		RESP_CODE.put(URL_LENGTH_ILLEGAL, "不合法的URL长度");
		RESP_CODE.put(GROUP_ID_ILLEGAL, "不合法的分组id");
		RESP_CODE.put(GROUP_NAME_ILLEGAL, "分组名字不合法");
		RESP_CODE.put(ACCESS_TOKEN_NULL, "缺少access_token参数");
		RESP_CODE.put(APPID_NULL, "缺少appid参数");
		RESP_CODE.put(REFRESH_TOKEN_NULL, "缺少refresh_token参数");
		RESP_CODE.put(SECRET_NULL, "缺少secret参数");
		RESP_CODE.put(MEDIA_FILE_DATA_NULL, "缺少多媒体文件数据");
		RESP_CODE.put(MEDIA_ID_NULL, "缺少media_id参数");
		RESP_CODE.put(SUB_MENU_DATA_NULL, "缺少子菜单数据");
		RESP_CODE.put(OAUTH_CODE_NULL, "缺少oauth code");
		RESP_CODE.put(OPENID_NULL, "缺少openid");
		RESP_CODE.put(ACCESS_TOKEN_TIMEOUT, "access_token超时");
		RESP_CODE.put(REFRESH_TOKEN_TIMEOUT, "refresh_token超时");
		RESP_CODE.put(OAUTH_CODE_TIMEOUT, "oauth_code超时");
		RESP_CODE.put(GET_NEED, "需要GET请求");
		RESP_CODE.put(POST_NEED, "需要POST请求");
		RESP_CODE.put(HTTPS_NEED, "需要HTTPS请求");
		RESP_CODE.put(USER_FOCUS_NEED, "需要接收者关注");
		RESP_CODE.put(FRIENDSHIP_NEED, "需要好友关系");
		RESP_CODE.put(MEDIA_FILE_NULL, "多媒体文件为空");
		RESP_CODE.put(POST_DATA_NULL, "POST的数据包为空");
		RESP_CODE.put(GRAP_MSG_NULL, "图文消息内容为空");
		RESP_CODE.put(TEXT_MSG_NULL, "文本消息内容为空");
		RESP_CODE.put(MEDIA_FILE_SIZE_OVER_LIMIT, "多媒体文件大小超过限制");
		RESP_CODE.put(MSG_OVER_LIMIT, "消息内容超过限制");
		RESP_CODE.put(TITLE_OVER_LIMIT, "标题字段超过限制");
		RESP_CODE.put(CONTENT_OVER_LIMIT, "描述字段超过限制");
		RESP_CODE.put(LINK_OVER_LIMIT, "链接字段超过限制");
		RESP_CODE.put(IMG_LINK_OVER_LIMIT, "图片链接字段超过限制");
		RESP_CODE.put(VOICE_PLAY_TIME_OVER_LIMIT, "语音播放时间超过限制");
		RESP_CODE.put(GRAP_MSG_OVER_LIMIT, "图文消息超过限制");
		RESP_CODE.put(REQUEST_OVER_LIMIT, "接口调用超过限制");
		RESP_CODE.put(MENU_COUNT_OVER_LIMIT, "创建菜单个数超过限制");
		RESP_CODE.put(REPLY_TIME_OVER_LIMIT, "回复时间超过限制");
		RESP_CODE.put(SYSTEM_GOURP_MODIFY_LIMIT, "系统分组，不允许修改");
		RESP_CODE.put(GROUP_NAME_LENGTH_OVER_LIMIT, "分组名字过长");
		RESP_CODE.put(GROUP_COUNT_OVER_LIMIT, "分组数量超过上限");
		RESP_CODE.put(MEDIA_DATA_NONE, "不存在媒体数据");
		RESP_CODE.put(MENU_VERSION_NONE, "不存在的菜单版本");
		RESP_CODE.put(MENU_DATA_NONE, "不存在的菜单数据");
		RESP_CODE.put(USER_NONE, "不存在的用户");
		RESP_CODE.put(PARSE_ERROE, "解析JSON/XML内容错误");
		RESP_CODE.put(API_UN_AUTH, "api功能未授权");
		RESP_CODE.put(API_USER_UN_AUTH, "用户未授权该api");
	}
	
	public static String getMessage(String code) {
        String ret = (String) RESP_CODE.get(code);
        if (ret == null) {
            ret = code;
        }
        return ret;
    }

}
