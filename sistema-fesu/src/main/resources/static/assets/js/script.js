(() => {
  'use strict'

  // Seleciona todos os formulários aos quais queremos aplicar estilos de validação personalizados do Bootstrap
  const forms = document.querySelectorAll('form.needs-validation')

  // Tratando evento de cada form
  Array.from(forms).forEach((form) => {
    form.addEventListener('submit', (event) => {
      if (!form.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
      }

      form.classList.add('was-validated');
    }, false);
  });
})();