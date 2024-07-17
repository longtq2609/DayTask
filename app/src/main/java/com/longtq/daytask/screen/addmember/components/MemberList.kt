package com.longtq.daytask.screen.addmember.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.longtq.daytask.R
import com.longtq.daytask.ui.theme.fordBlue
import com.longtq.daytask.ui.theme.white
import com.longtq.daytask.util.components.AppText
import com.longtq.domain.entity.User

@Composable
fun MemberList(
    users: List<User>,
    onMemberChecked: (User) -> Unit,
    modifier: Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_8))
    ) {
        items(users) { user ->
            MemberItem(user) { updatedUser ->
                onMemberChecked(updatedUser)
            }
        }
    }
}

@Composable
fun MemberItem(user: User, onMemberChecked: (User) -> Unit) {
    var isChecked by remember { mutableStateOf(user.isChecked ?: false) }

    Row(
        modifier = Modifier
            .background(fordBlue)
            .padding(dimensionResource(id = R.dimen.dimen_8))
            .height(dimensionResource(id = R.dimen.dimen_40)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = user.avatar,
            contentDescription = null,
            modifier = Modifier.size(dimensionResource(id = R.dimen.dimen_40))
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.dimen_8)))
        AppText(
            text = user.displayName ?: user.userName ?: "",
            fontWeight = FontWeight.Medium,
            fontSize = dimensionResource(
                id = R.dimen.dimen_14
            ).value.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Checkbox(
            checked = isChecked,
            onCheckedChange = { checked ->
                isChecked = checked
                user.isChecked = checked
                onMemberChecked(user)
            },
            colors = CheckboxDefaults.colors(
                checkedColor = white,
                uncheckedColor = white
            )
        )
    }
}

@Preview
@Composable
fun MemberItemPreview() {
    MemberItem(user = User(isChecked = true), onMemberChecked = { })
}
