package com.users.component.config.mysql;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration//表示这个类为一个配置类
// 配置mybatis的接口类放的地方
//@MapperScan(basePackages = {"com.users.modules.***.mapper.primary"}, sqlSessionTemplateRef  = "primarySqlSessionTemplate")
@MapperScan(basePackages = {"com.users.modules.mapper.primary"}, sqlSessionTemplateRef  = "primarySqlSessionTemplate")
//@MapperScan(basePackages = {"***.***.***.***.mapper.primary"}, sqlSessionTemplateRef  = "primarySqlSessionTemplate")
public class DataSourcePrimaryConfig {

    @Bean(name = "primaryDataSource")// 将这个对象放入Spring容器中
    @ConfigurationProperties(prefix = "spring.datasource.primary")// 读取application.properties中的配置参数映射成为一个对象,prefix表示参数的前缀
    @Primary// 表示这个数据源是默认数据源
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "primarySqlSessionFactory")
    @Primary
    // @Qualifier表示查找Spring容器中名字为test1DataSource的对象
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 设置mybatis的xml所在位置
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/users/modules/**/mapper/primary/*.xml"));
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/users/modules/mapper/primary/*.xml"));
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:**/**/**/**/mapper/primary/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "primaryTransactionManager")
    @Primary
    public DataSourceTransactionManager primaryTransactionManager(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "primarySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate primarySqlSessionTemplate(@Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
