using System.Collections.Generic;
using System.Windows;
using Frontend.Models;

namespace Frontend
{
    public partial class ResumeListWindow : Window
    {
        public ResumeListWindow(List<Resume> resumes)
        {
            InitializeComponent();
            LoadResumes(resumes);
        }

        private void LoadResumes(List<Resume> resumes)
        {
            ResumeListBox.Items.Clear();
            foreach (var resume in resumes)
            {
                ResumeListBox.Items.Add(resume.ToString());
            }
        }
    }
}
