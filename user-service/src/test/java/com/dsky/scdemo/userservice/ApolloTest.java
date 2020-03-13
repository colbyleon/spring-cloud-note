package com.dsky.scdemo.userservice;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;

import java.util.concurrent.TimeUnit;

/**
 * @author colby.luo
 * @date 2020/3/10 16:14
 */
public class ApolloTest {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("env", "DEV");

        Config config = ConfigService.getAppConfig();
        String key = "username";
        String defaultValue = "colby";
        String username = config.getProperty(key, defaultValue);
        System.out.println("username = " + username);

        config.addChangeListener(event -> {
            System.out.println("发生修改数据的命名空间是:" + event.getNamespace());
            for (String changeKey : event.changedKeys()) {
                ConfigChange change = event.getChange(changeKey);
                System.out.println(String.format("修改了 %s ,原值 %s, 新值 %s, 类型 %s",
                        change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
            }
        });

        TimeUnit.HOURS.sleep(1);
    }
}
