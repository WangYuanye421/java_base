package org.wyy.tech.reflect.plugin;

/**
 * @author Wang Yuanye
 * @version v1.0
 * @apiNote 语言插件
 * @date : 2021/3/11 下午5:21
 **/
public class LangPlugin extends PluginTemplate{

    @Override
    protected void printInfo(String data) {
        System.out.println("插件信息: 皮肤插件. " +
                "数据:" +data);
    }

    @Override
    protected boolean needBeforeProcess() {
        return false;
    }

    @Override
    protected Object beforeProcess(String data) {
        return data + ";beforeProcess 已处理";
    }

    @Override
    protected Object process(String data) {
        return data + ";process 已处理";
    }

    @Override
    protected Object postProcess(String data) {
        data = data + ";postProcess 已处理";
        System.out.println(data);
        return data;
    }
}
