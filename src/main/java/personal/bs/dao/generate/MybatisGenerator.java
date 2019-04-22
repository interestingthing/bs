package personal.bs.dao.generate;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * mybatis配置文件自动生成
 *
 * mybatis generator 可以通过maven插件的方式运行， 也可以通过运行该测试main函数
 */
public class MybatisGenerator {
    public static void main(String[] args) throws InterruptedException, SQLException, IOException, InvalidConfigurationException, XMLParserException {

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;

        File file =new File("");
        System.out.println(file.getAbsolutePath());
        File configFile = new File(file.getAbsolutePath() + "/src/main/resources/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
