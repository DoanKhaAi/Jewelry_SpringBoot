package javaweb.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AsyncConfig {
   //Em kích hoạt chức năng bất đồng bộ để khi xử lý gửi mail sẽ không để người dùng đợi render giao diện lâu
}