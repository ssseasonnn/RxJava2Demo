package zlc.season.rxjava2demo.entity;

/**
 * Author: Season(ssseasonnn@gmail.com)
 * Date: 2016/12/9
 * Time: 16:13
 * FIXME
 */
public class UserInfo {
    UserBaseInfoResponse mBaseInfo;
    UserExtraInfoResponse mExtraInfo;

    public UserInfo(UserBaseInfoResponse baseInfo, UserExtraInfoResponse extraInfo) {
        mBaseInfo = baseInfo;
        mExtraInfo = extraInfo;
    }
}
