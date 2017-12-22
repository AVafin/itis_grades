package webapp;

import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Configuration;
import freemarker.template.Version;

import javax.servlet.ServletContext;

public class ConfigSingleton {

    private static Configuration cfg = null;

    public static Configuration getConfig(ServletContext sc) {
        if (cfg == null) {
            cfg = new Configuration(new Version(2, 3, 23));
            cfg.setServletContextForTemplateLoading(sc, "/WEB-INF/templates");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        }
        return cfg;
    }
}
