var article = {
  // 초기화(이벤트 등록) 메소드
  init: function() {

    var _this = this;

    const createBtn = document.querySelector('#article-create-btn');
    const updateBtn = document.querySelector('#article-update-btn');
    const destroyBtn = document.querySelector('#article-destroy-btn');

    if (createBtn != null) {
      createBtn.addEventListener('click', _this.create);
    }
    if (updateBtn) {
      updateBtn.addEventListener('click', _this.update);
    }
    if (destroyBtn) {
      destroyBtn.addEventListener('click', _this.destroy);
    }
  },

  create: function() {

    var data = {
      title: document.querySelector('#article-title').value,
      content: document.querySelector('#article-content').value,
    };

    fetch('/api/articles', {
      method: 'POST',
      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(function(response) {

      if (response.ok) {
        alert('게시글이 작성 되었습니다.');
        window.location.href='/articles';
      } else {
        alert('게시글 작성에 문제가 생겼습니다.');
      }
    });
  },

  update: function() {

    var data = {
      id: document.querySelector('#article-id').value,
      title: document.querySelector('#article-title').value,
      content: document.querySelector('#article-content').value,
    };

    fetch('/api/articles/' + data.id, {
      method: 'PUT',
      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(function(response) {

      if (response.ok) {
        alert('게시글이 수정 되었습니다.');
        window.location.href='/articles/' + data.id;
      } else {
        alert('게시글 수정에 문제가 생겼습니다.');
      }
    });
  },

  destroy: function() {

      var split = location.pathname.split('/');
      var id = split[split.length - 1];

      fetch('/api/articles/' + id, {
        method: 'DELETE',
      }).then(function(response) {

        if (response.ok) {
          alert('글이 삭제 되었습니다.');
          window.location.href='/articles';
        } else {
          alert('글을 삭제할 수 없습니다.');
        }
      });
  }
};

article.init();