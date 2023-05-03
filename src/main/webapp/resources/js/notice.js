/**
 * 
 */
        const modal= document.querySelector('.js-modal.notice')
        const modalClose=document.querySelectorAll('.js-modal-close')
        const modalContainer= document.querySelector('.js-modal-container')
		const modal_content = document.getElementById("modal-content")

        function showNotice(content)
        {
			modal_content.innerHTML=content
            modal.classList.add('open')
        }
        function hideNotice()
        {
            modal.classList.remove('open')
        }
       
       
        modalContainer.addEventListener('click', function (event) {event.stopPropagation()} )
           
       
