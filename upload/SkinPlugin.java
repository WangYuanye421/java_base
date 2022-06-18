
import reflect.plugin.PluginTemplate;
/**
 * @author Wang Yuanye
 * @version v1.0
 * @apiNote 皮肤插件
 * @date : 2021/3/11 下午5:21
 **/
public class SkinPlugin extends PluginTemplate{

    protected void printInfo(String data) {
        System.out.println("插件信息: 皮肤插件. " +
                "数据:" +data);
    }

    protected Object beforeProcess(String data) {
        data = data + ";beforeProcess 已处理";
        return data;
    }

    protected Object process(String data) {
        return data + ";process 已处理";
    }

    protected Object postProcess(String data) {
        data = data + ";postProcess 已处理";
        System.out.println(data);
        return data;
    }
}
