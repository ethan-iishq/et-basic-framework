package ethan.etframework.service.impl;

import ethan.etframework.entity.Person;
import ethan.etframework.mapper.PersonMapper;
import ethan.etframework.service.PersonService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ethanx
 * @since 2017-12-08
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

}
