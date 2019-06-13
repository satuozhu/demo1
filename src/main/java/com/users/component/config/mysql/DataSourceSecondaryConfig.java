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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
//@MapperScan(basePackages = {"com.users.modules.***.mapper.secondary"}, sqlSessionTemplateRef  = "secondarySqlSessionTemplate")
@MapperScan(basePackages = {"com.users.modules.mapper.secondary"}, sqlSessionTemplateRef  = "secondarySqlSessionTemplate")
//@MapperScan(basePackages = {"***.***.***.***.mapper.secondary"}, sqlSessionTemplateRef  = "secondarySqlSessionTemplate")
public class DataSourceSecondaryConfig {

    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondarySqlSessionFactory")
    public SqlSessionFactory secondarySqlSessionFactory(@Qualifier("secondaryDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/users/modules/**/mapper/secondary/*.xml"));
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/users/modules/mapper/secondary/*.xml"));
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:**/**/**/**/mapper/secondary/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "secondaryTransactionManager")
    public DataSourceTransactionManager secondaryTransactionManager(@Qualifier("secondaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "secondarySqlSessionTemplate")
    public SqlSessionTemplate secondarySqlSessionTemplate(@Qualifier("secondarySqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
