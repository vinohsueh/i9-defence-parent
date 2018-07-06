package i9.defence.platform.socket.test;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptEngineTest {

    public static void main(String args[]) throws ScriptException, NoSuchMethodException {
//        final ScriptEngineManager mgr = new ScriptEngineManager();
//        for (ScriptEngineFactory fac : mgr.getEngineFactories()) {
//            System.out.println(String.format("%s (%s), %s (%s), %s", fac.getEngineName(), fac.getEngineVersion(),
//                    fac.getLanguageName(), fac.getLanguageVersion(), fac.getParameter("THREADING")));
//        }
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        Bindings bindings = engine.createBindings(); // Local级别的Binding
        String script = "function getVal(name) { return name + \"_999\"};T = (\"100\" + F + ${hostname}) + getVal(\"\")"; // 定义函数并调用
        CompiledScript JSFunction = null; // 解析编译脚本函数
        try {
        Compilable compilable = (Compilable) engine;
            JSFunction = compilable.compile(script);
            bindings.put("F", 2.5);
            bindings.put("${hostname}", 2.5);
            JSFunction.eval(bindings);
            System.out.println(bindings.get("T"));
        } catch (ScriptException e) {
            e.printStackTrace();
        }

    }
}
