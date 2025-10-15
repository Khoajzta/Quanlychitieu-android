import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quanlychitieu.Components.CardKhoanChi
import com.example.quanlychitieu.Utils.listKhoanChiConst.listKhoanChi
import com.example.quanlychitieu.domain.model.KhoanChiModel
import com.example.quanlychitieu.ui.theme.Dimens.PaddingBody


@Composable
fun KhoanChiColumn(
    listKhoanChi: List<KhoanChiModel>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingBody),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        if(listKhoanChi.isNullOrEmpty()){
            Text(
                text = "Chưa có khoản chi nào"
            )
        }else{
            for (i in listKhoanChi.indices) {
                CardKhoanChi(
                    item = listKhoanChi[i],
                    modifier = Modifier
                )
            }
        }

    }
}

@Preview
@Composable
fun PreviewCardKhoanChi() {
    KhoanChiColumn(
        listKhoanChi,
    )

}