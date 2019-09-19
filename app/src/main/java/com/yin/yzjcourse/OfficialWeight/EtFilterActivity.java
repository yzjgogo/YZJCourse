package com.yin.yzjcourse.OfficialWeight;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;
import com.yin.yzjcourse.utils.DLog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EtFilterActivity extends BaseActivity {

    @BindView(R.id.et)
    EditText et;
    private InputFilter filter = new InputFilter() {
        /**
         * 用于过滤用户一次性要输入到EditText的文本，如果符合条件则filter()方法返回null即可，EditText就回接收这一次性的输入；
         * 如果不符合条件则filter()方法返回""(空字符串即可)，EditText不会接收这一次性输入；
         * 从别处复制过来要粘贴到EditText也是同样的道理。
         * 需要注意的是，filter过滤的是source整体，只有source整体符合条件才允许输入到EditText，source中有任何一个字符不符合条件，都不能输入到EditText。
         * @param source 对于输入，就是用户的一次性输入的内容，所谓一次性输入，即用户可能在键盘上键入了很长一段文本，然后一次性放入EditText；
         *                对于通过光标删除，则source为""(空字符串)
         * @param start 对于输入，总是0；
         *               对于通过光标删除，则start为0；
         * @param end 对于输入，总是source的长度，其实[start,end)就是source的字符区间；
         *             对于通过光标删除，则start为0；
         * @param dest 对于输入，输入source之前，EditText原本已经存在的文本字符串；
         *              对于删除，删除之前，EditText原本已经存在的文本字符串
         * @param dstart 对于输入，总是dest的长度；
         *                对于删除，就是dest中要被删除的字符的index(从0开始)；
         * @param dend 对于输入，总是dest的长度，其实dstart和dend是一样的；
         *              对于删除,就是dest中要被删除的字符的index+1;
         * @return
         */
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            DLog.eLog("过滤：source = "+source+", start = "+start+", end = "+end+", dest = "+dest+", dstart = "+dstart+", dend = "+dend);
            for (int i = start; i < end; i++) {
                if (!Utils.isChinese(source.charAt(i))) {
                    //我的目的想EditText，只接收中文，source中有任何一个字符不是中文字符则返回"",拒绝整个source的输入。
                    return "";
                }
            }
            return null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_et_filter);
        ButterKnife.bind(this);
        /**
         * setFilters方法接收一个InputFilter类型的数组，即你可以定义多个过滤规则；
         * LengthFilter：用于限定EditText的最大长度。
         */
        et.setFilters(new InputFilter[]{filter,new InputFilter.LengthFilter(5)});
    }
}
