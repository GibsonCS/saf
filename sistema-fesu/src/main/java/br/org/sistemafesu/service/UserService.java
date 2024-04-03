package br.org.sistemafesu.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.sistemafesu.entity.User;
import br.org.sistemafesu.repository.UserRepository;
import lombok.NonNull;

@Service
public class UserService extends AbstractService<User, UserRepository> {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

    public void setPassword(@NonNull Long id, @NonNull String password) {
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
    public User update(@NonNull Long id, @NonNull User model) {
        if (model.getId() == null || !repository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado!");
        }

        return repository.save(model);
    }

    public void saveUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
    }

    @Override
    public User save(User model) {
        model.setPassword(encoder.encode(model.getPassword()));

        return super.save(model);
    }
}
