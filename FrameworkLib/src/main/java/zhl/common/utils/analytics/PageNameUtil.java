package zhl.common.utils.analytics;

import java.util.HashMap;
import java.util.Map;

/**
 * 页面名称
 * Created by HB on 2017/7/19.
 */

public class PageNameUtil {

    public static Map<String, String> map = new HashMap<>();

    static {
        map.put("FrameHomeActivity", "首页");
        map.put("LoginActivity", "登录");
        map.put("ForgetPwdActivity", "忘记密码");
        map.put("RegistActivity", "注册");
        map.put("ChooseBookActivity", "选择课本");
        map.put("WebViewActivity", "网页");
        map.put("CommonWebViewActivity", "通用的网页");
        map.put("ClassMainActivity", "学习圈主");
        map.put("StudentsActivity", "学生动态");
        map.put("StudentRecordActivity", "学生录音详情");
        map.put("PriseInfoActivity", "学习圈点赞详情");
        map.put("ClassMessageActivity", "班级动态消息");
        map.put("MeMainActivity", "我的");
        map.put("HomeworkMainActivity", "做作业");
        map.put("HomeworkHistoryActivity", "历史作业列表");
        map.put("HomeworkReciteWordsActivity", "口语练习Main");
        map.put("HomeworkReciteBookActivity", "做作业-背诵");
        map.put("HomeworkReadBookActivity", "做作业-熟读 ");
        map.put("ReadBookActivity", "点读模块");
        map.put("PaperTestActicity", "考试");
        map.put("PKRobotActivity", "pk 邀请弹框");
        map.put("SpokenMainActivity", "练口语关卡选择");
        map.put("SpokenEmigratedActivity", "课文闯关");
        map.put("SpokenEmResultActicity", "课文闯关结果");
        map.put("SpokenPkActivity", "课文PK");

        map.put("SpokenPkResultActivity", "课文PK结果");
        map.put("SpokenPkResultDetailActivity", "课文PK结果详情");
        map.put("SpokenPkRecordActivity", "课文PK记录");
        map.put("SpokenRankListActivity", "口语课文排行榜");
        map.put("SpokenChallengeSelfActivity", "自我挑战");
        map.put("SpokenChallengeSelfResultActivity", "自我挑战结果");
        map.put("FeedbackMainActivity", "学习反馈");

        map.put("ChooseClassActivity", "选择班级");
        map.put("SelectActivity", "选择省市区");
        map.put("MeUpdatePwdActivity", "修改密码");
        map.put("MeUpdateTelActivity", "修改手机绑定");
        map.put("MeUserInfoActivity", "个人信息展示");
        map.put("MeEditeUserinfoActivity", "个人信息修改");
        map.put("MessageMainActivity", "消息列表");
        map.put("MeSettingActivity", "设置");
        map.put("ApkUpdateActivity", "apk更新");
        map.put("WordTipActivity", "学习点拨");
        map.put("QuestionTipActivity", "挑战一下、小试一下过渡");
        map.put("OutwardScoreActivity", "完成课时结果");

        map.put("OutwardLessonActivity", "拓展-课程课时");
        map.put("OutwardWordsActivity", "拓展-单词");
        map.put("OutwardPracticeActivity", "拓展-练习题");
        map.put("BindRealnameActivity", "同学认领");
        map.put("WordsActivity", "流程单词页面");
        map.put("VideoActivity", "看动画");
        map.put("LisTranslateActivity", "听力原文");
        map.put("ReadLessonActivity", "朗读课文");

        map.put("PracticeListActivity", "语法学习列表");
        map.put("CourseCatalogListActivity", "课程种类列表");
        map.put("PracticeQuestionActivity", "训练错题");
        map.put("UnitPartListActivity", "课程目录");
        map.put("StudyGuideActivity", "单元学习列表");

        map.put("CourseSpokenEmigratedActivity", "课文闯关模块");
        map.put("ReadBookCampaignActivity", "活动手册");
        map.put("UnitWordsActivity", "单词页面");
        map.put("CourseQuestionScoreActivity", "课时完成时显示的结果");
        map.put("ExamQuestionActivity", "考试模式-流程");
        map.put("DubSeriesDetailActivity", "配音系列分类详情");
        map.put("StudyWrongQuestionActivity", "消灭错题");
        map.put("MsgDetailActivity", "消息详情");

        map.put("CourseReciteBookActivity", "做作业背诵");
        map.put("CollectionWrongQuestionActivity", "丢分题");
        map.put("WatchQueActivity", "看问题");
        map.put("DubActivity", "配音详情");
        map.put("UserDubActivity", "用户配音");
        map.put("DubCourseDetailActivity", "配音课程详细");
        map.put("DubCompletedActivity", "配音发布");
        map.put("DubbingActivity", "配音页");
        map.put("PhoneBindActivity", "手机绑定");
        map.put("PopupPushActivity", "阿里辅助通道");
        map.put("MyDubbingListActivity", "我的配音列表");
        map.put("RechargeMoneyActivity", "充值");
        map.put("RechargeChannelActivity", "支付详情");
        map.put("RechargeCallbackActivity", "支付结果查询页回调");
        map.put("WXPayEntryActivity", "微信支付按sdk要求的回调类");
        map.put("StudentInfoFragmentActivity", "学生详情");
        map.put("GuideActivity", "首页引导");
        map.put("StudyToolsSearchActivity", "学习工具搜索");
        map.put("StudyToolsActivity", "学习工具");
        map.put("MultipleChooseBookActivity", "选择课本");
        map.put("SelectThemeActivity", "选择喜欢的专题");
        map.put("SpokenPracticeActivity", "口语练习模块");
        map.put("AudioScoreIntroductionActivity", "配音页面-非会员-专业评分");

        map.put("CourseOrchardPreviewActivity", "果园-预览页");
        map.put("HomeExtraCourseOrchardActivity", "果园-课外辅导");
        map.put("ExactSearchClassActivity", "加入班级-精确查找");
        map.put("ChooseTagActivity", "选择兴趣标签");
        map.put("AddUserTagActivity", "添加用户自定义标签");

        /*-----------Fragment----------------*/
        map.put("CollectionQuestionFt", "收藏题目展示");
        map.put("CollectQuesFragment", "精选收藏");
        map.put("WrongQuesFragment", "错题集");
        map.put("WrongQuestionFt", "旧的错题集");

        map.put("GuideFragment", "引导");
        map.put("HomeDubFragment", "首页-乐配音");
        map.put("HomeExtraCourseFragment", "首页-课外辅导");
        map.put("HomeHomeworksFragment", "首页-老师作业");
        map.put("HomeInnerCourseFragment", "首页-课内学习");
        map.put("HomeTeacherFragment", "首页-快乐暑假");
        map.put("HomeworkFragment", "作业详情");
        map.put("MclassBillboardFragment", "学习圈排行榜");
        map.put("StudentGiftMessageFragment", "学生礼物信息");
        map.put("StudentMessageFragment", "动态-班级-学生消息");
        map.put("GiftFragment", "礼物");
        map.put("MessagePKFragment", "我的消息—PK的消息、赞的消息");
        map.put("MessageSysFragment", "我的消息-系统通知");
        map.put("StudentPkRecordFragment", "我的空间-闯关和PK");
        map.put("UserDubListFragment", "用户配音列表");
        map.put("OutwardWordsFragment", "拓展课程单词");
        map.put("HomeworkHistoryFragment", "历史作业详情");
        map.put("SpokenLessonFragment", "口语练习");
        map.put("WordFragment", "单词");
        map.put("StudyToolsCalendarFragment", "学习工具-日历");
        map.put("StudyToolsListFragment", "学习工具-列表");
        map.put("StudyToolsSearchFragment", "学习工具-数字学习");
        map.put("StudyToolsTimeFragment", "学习工具-时间学习");
        map.put("WordPreviewFragment", "单词听写-预览");
        map.put("MembersDubCompletedFragment", "配音结果页-会员");
        map.put("UnMembersCompletedFragment", "配音结果页-非会员");
    }


    public static String getPageName(String pageName) {
        /*if (map.get(pageName) == null && !TextUtils.isEmpty(AbsResult.urlPrefix)) {
            throw new RuntimeException("阿里事件统计，PageNameUtil 没有默认的页面名称==" + pageName);
        }*/
        return map.get(pageName) == null ? "" : map.get(pageName) + "页面";
    }
}
