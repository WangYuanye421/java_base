package reflect.plugin;

/**
 * @author Wang Yuanye
 * @version v1.0
 * @apiNote 插件模板类
 * @date : 2021/3/11 下午5:07
 **/
public abstract class PluginTemplate {
    private String data;

    // 模板方法
    protected final void handle(){
        printInfo(data);
        if (needBeforeProcess()) {
            data = (String)beforeProcess(data);
        }
        data = (String)process(data);
        if (needPostProcess()) {
            postProcess(data);
        }
    }

    public void setData(String data) {
        this.data = data;
    }

    // 打印插件信息
    protected abstract void printInfo(String data);

    // 钩子方法,决定模板处理流程
    protected boolean needBeforeProcess() {
        return true;
    }

    protected boolean needPostProcess() {
        return true;
    }

    // 前置处理:如数据处理等
    protected abstract Object beforeProcess(String data);

    // 数据处理
    protected abstract Object process(String data);


    // 后置处理
    protected abstract Object postProcess(String data);
}
