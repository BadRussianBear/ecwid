package com.carllewis14.repos.model

/**
 * Data class for repo json response
 */
data class Repo( var id: Long,val node_id: String?, val name: String?, val full_name: String?,
                 val private: Boolean?, val owner: User?, val html_url: String?, val description: String?,
                 val fork: Boolean?, val url: String?, val forks_url: String?,
                 val keys_url: String?, val collaborators_url: String?, val teams_url: String?,
                 val hooks_url: String?, val issue_events_url: String?,
                 val events_url: String?, val assignees_url: String?, val branches_url: String?, val tags_url: String?,
                 val blobs_url: String?, val git_tags_url: String?, val git_refs_url: String?,
                 val trees_url: String?, val statuses_url: String?, val languages_url: String?,
                 val stargazers_url: String?, val contributors_url: String?, val subscribers_url: String?,
                 val subscription_url: String?, val commits_url: String?, val git_commits_url: String?, val comments_url: String?,
                 val issue_comment_url: String?, val contents_url: String?, val compare_url: String?, val merges_url: String?,
                 val archive_url: String?, val downloads_url: String?, val issues_url: String?, val pulls_url: String?,
                 val milestones_url: String?, val notifications_url: String?, val labels_url: String?, val releases_url: String?,
                 val deployments_url: String?, val created_at: String?, val updated_at: String?, val pushed_at: String?,
                 val git_url: String?, val ssh_url: String?, val clone_url: String?, val svn_url: String?, val homepage: String?,
                 val size: Number?, val stargazers_count: Number?, val watchers_count: Number?,
                 val language: String?, val has_issues: Boolean?, val has_projects: Boolean?, val has_downloads: Boolean?,
                 val has_wiki: Boolean?, val has_pages: Boolean?, val forks_count: Number?, val mirror_url: Any?,
                 val archived: Boolean?, val disabled: Boolean?, val open_issues_count: Number?,  val forks: Number?,
                 val open_issues: Number?, val watchers: Number?, val default_branch: String?)