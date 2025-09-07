( () => {
    'use strict';

    document.querySelector( '.navbar-toggler' ).addEventListener( 'click', () => {
        document.querySelector( '.offcanvas-collapse' ).classList.toggle( 'open' );
    } );
} )();