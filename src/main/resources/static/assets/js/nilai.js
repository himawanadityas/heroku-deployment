var tableBiodata = {
    create: function () {
        // jika table tersebut datatable, maka clear and dostroy
        if ($.fn.DataTable.isDataTable('#table-nilai')) {
            //table yg sudah dibentuk menjadi datatable harus d rebuild lagi untuk di instantiasi ulang
            $('#table-nilai').DataTable().clear();
            $('#table-nilai').DataTable().destroy();
        }

        $.ajax({
            url: '/api/nilai',
            method: 'get',
            contentType: 'application/json',
            success: function (res, status, xhr) {
                if (xhr.status == 200 || xhr.status == 201) {
                    $('#table-nilai').DataTable({
                        data: res,
                        columns: [
                            {
                                title: "Nama",
                                data: "mahasiswa"
                            },
                            {
                                title: "Ujian",
                                data: "ujian"
                            },

                            {
                                title: "Nilai",
                                data: "nilai"
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
        $('#form')[0].reset();
        $("#id").val("");
        $("#idDetail").val("");
    },
    saveForm: function () {
        if ($('#form').parsley().validate()) {
            var dataResult = getJsonForm($("#form").serializeArray(), true);

            $.ajax({
                url: '/api/nilai',
                method: 'post',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(dataResult),
                success: function (res, status, xhr) {
                    if (xhr.status == 200 || xhr.status == 201) {
                        tableBiodata.create();
                        $('#modal-nilai').modal('hide')
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
            url: '/api/nilai/' + idCabang,
            method: 'get',
            contentType: 'application/json',
            dataType: 'json',
            success: function (res, status, xhr) {
                if (xhr.status == 200 || xhr.status == 201) {
                    $('#form').fromJSON(JSON.stringify(res));
                    $('#modal-nilai').modal('show')

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
      url: '/api/nilai/' + idCabang,
      method: 'get',
      contentType: 'application/json',
      dataType: 'json',
      success: function (res, status, xhr) {
        if (xhr.status == 200 || xhr.status == 201) {
          $('#form').fromJSON(JSON.stringify(res));
          $('#delete-nilai').modal('show')

        } else {

        }
      },
      erorrr: function (err) {
        console.log(err);
      }
    });

  },
  deleteDataRow: function () {
    if ($('#form').parsley().validate()) {
      var dataResult = getJsonForm($("#form").serializeArray(), true);

      $.ajax({
        url: '/api/nilai/' + dataResult.id,
        method: 'delete',
        success: function () {
            tableBiodata.create();
            $('#btn-delete-nilai').modal('hide')

          },
        erorrr: function (err) {
          console.log(err);
        }
      });
    }
  }

};
