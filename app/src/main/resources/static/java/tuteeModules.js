$(document).ready(function() {
    // Select the modules select element
    var moduleSelect = $('#modules');
    moduleSelect.hide();
    $('#role').change(function() {
        if ($(this).val() === 'tutor') {
            // Show the modules select element
            moduleSelect.show();
        } else {
            // Hide the modules select element
            moduleSelect.hide();
        }
    });
});
