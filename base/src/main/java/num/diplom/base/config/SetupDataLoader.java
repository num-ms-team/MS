package num.diplom.base.config;

import num.diplom.base.repository.UserRepository;
import num.diplom.base.repository.model.MongoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;
    private final UserRepository userRepository;

    @Autowired
    public SetupDataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!alreadySetup) {
            userRepository.save(new MongoUser("admin"));
        }
    }
}
