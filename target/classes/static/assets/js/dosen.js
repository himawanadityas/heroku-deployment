var tableBiodata = {
    create: function () {
        // jika table tersebut datatable, maka clear and dostroy
        if ($.fn.DataTable.isDataTable('#table-dosen')) {
            //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
            $('#table-dosen').DataTable().clear();
            $('#table-dosen').DataTable().destroy();
        }

        $.ajax({
            url: '/api/dosen',
            method: 'get',
            contentType: 'application/json',
            success: function (res, status, xhr) {
                if (xhr.status == 200 || xhr.status == 201) {
                    $('#table-dosen').DataTable({
                        data: res,
                        columns: [
                            {
                                title: "Nama Dosen",
                                data: "namaDosen"
                            },
                            {
                                title: "Jurusan",
                                data: "jurusan"
                            },
                            {
                                title: "Type Dosen",
                                data: "typeDosen"
                             },
                            {
                                title: "Action",
                                data: null,
                                render: function (data, type, row) {
                                    return "<button class='btn-primary btn-dark' onclick=formBiodata.setEditData('" + data.id + "')>Edit</button>" +
                                      "<button class='btn-primary btn-danger' onclick=deleteData.deleteModalConfirm('" + data.id + "')>Delete</button>"
                                }
                            }
                        ]
                    });

                } else {

                }
            },
            error: function (err) {
                console.log(err);
            }
        });


    },

};

var formBiodata = {
    resetForm: function () {
        $('#form-biodata')[0].reset();
        $("#id").val("");
        $("#idDetail").val("");
    },
    saveForm: function () {
        if ($('#form-biodata').parsley().validate()) {
            var dataResult = getJsonForm($("#form-biodata").serializeArray(), true);

            $.ajax({
                url: '/api/dosen',
                method: 'post',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(dataResult),
                success: function (res, status, xhr) {
                    if (xhr.status == 200 || xhr.status == 201) {
                        tableBiodata.create();
                        $('#modal-dosen').modal('hide')
                    } else {

                    }
                },
                erorrr: function (err) {
                    console.log(err);
                }
            });
        }
    },
  setEditData: function (idCabang) {
        formBiodata.resetForm();

        $.ajax({
            url: '/api/dosen/' + idCabang,
            method: 'get',
            contentType: 'application/json',
            dataType: 'json',
            success: function (res, status, xhr) {
                if (xhr.status == 200 || xhr.status == 201) {
                    $('#form-biodata').fromJSON(JSON.stringify(res));
                    $('#modal-dosen').modal('show')

                } else {

                }
            },
            erorrr: function (err) {
                console.log(err);
            }
        });


    }

};
var deleteData = {
  deleteModalConfirm: function (idCabang) {
    $.ajax({
      url: '/api/dosen/' + idCabang,
      method: 'get',
      contentType: 'application/json',
      dataType: 'json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#form-biodata').fromJSON(JSON.stringify(res));
          $('#delete-dosen').modal('show')

        } else {

        }
      },
      erorrr: function (err) {
        console.log(err);
      }
    });

  },
  deleteDataRow: function () {
    if ($('#form-biodata').parsley().validate()) {
      var dataResult = getJsonForm($("#form-biodata").serializeArray(), true);

      $.ajax({
        url: '/api/dosen/' + dataResult.id,
        method: 'delete',
        success: function () {
            tableBiodata.create();
            $('#delete-dosen').modal('hide')

          },
        erorrr: function (err) {
          console.log(err);
        }
      });
    }
  }

};
