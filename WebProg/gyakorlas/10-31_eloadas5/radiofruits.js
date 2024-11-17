document.querySelector("form").addEventListener('click', function (e) {
    console.log("clicked")
    if (e.target.matches('[type=radio]')) {
      // RadioNodeList.value
      const value = document.querySelector('form').elements['fruit'].value
      console.log(value);
    }
  })

  const input = document.querySelector('#textinput1')
  document.querySelector('#btn1').addEventListener('click', function (e) {
      console.log(input.selectionStart, input.selectionEnd)
  })
  document.querySelector('#btn2').addEventListener('click', function (e) {
      input.setSelectionRange(2, 5)
  })