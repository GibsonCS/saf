package br.org.sistemafesu.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.sistemafesu.entity.User;
import br.org.sistemafesu.repository.UserRepository;

@Service
public class UserService extends AbstractService<User, UserRepository> {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(User user, UserRepository userRepository) {
        super(user, userRepository);
    }

    public void setPassword(Long id, String password) {
        User user = repository.findById(id).orElse(null);

        if (user != null) {
            user.setPassword(encoder.encode(password));
            repository.save(user);
        }
    }

    public User getUserUsername(String username){
       return repository.findByUsername(username);
    }

    @Override
    public User update(Long id, User model) {
        if (model.getId() == null || !repository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado!");
        }

        return repository.save(model);
    }

    @Override
    public User save(User model) {
        model.setPassword(encoder.encode(model.getPassword()));

        return super.save(model);
    }
}
