package reflect.plugin;


/**
 * @author Wang Yuanye
 * @version v1.0
 * @apiNote 测试类
 * @date : 2021/3/11 下午1:51
 **/
public class MainTest {
    public static void main(String[] args) {
        PluginTemplate plugin = new LangPlugin();
        plugin.setData("6666");
        plugin.handle();
    }
}
