package zerobase.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration    // config 파일이다
@EnableSwagger2   // swagger 활성화
public class SwaggerConfig {

    /*
    개발자용 admin controller 만들어서 사용시
    클라이언트에게는 admin controller 가 안보이게 지정
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.any())   // basic-error-controller 까지 뜬다
                .apis(RequestHandlerSelectors.basePackage("zerobase.weather"))   // diary-controller 만 뜬다
                .paths(PathSelectors.any())   // 모든 경로의 API 를 보여주겠다
//                .paths(PathSelectors.ant("/read/**"))   // "/read" 로 시작하는것만 보여주겠다
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("날씨 일기 프로젝트 :)")
                .description("날씨 일기를 CRUD 할 수 있는 백엔드 API 입니다")
                .version("2.0")
                .build();
    }

}
